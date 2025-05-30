// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

using System;
using System.ClientModel.Primitives;
using System.Collections.Generic;
using System.Linq;
using Microsoft.TypeSpec.Generator.ClientModel.Providers;
using Microsoft.TypeSpec.Generator.Input;
using Microsoft.TypeSpec.Generator.Primitives;
using Microsoft.TypeSpec.Generator.Tests.Common;
using NUnit.Framework;

namespace Microsoft.TypeSpec.Generator.ClientModel.Tests.Providers
{
    public class ClientOptionsProviderTests
    {
        private const string ApiVersionsCategory = "WithApiVersions";

        [SetUp]
        public void SetUp()
        {
            var categories = TestContext.CurrentContext.Test?.Properties["Category"];
            bool containsApiVersions = categories?.Contains(ApiVersionsCategory) ?? false;

            // Load the mock generator with or without api versions
            if (containsApiVersions)
            {
                List<string> apiVersions = ["1.0", "2.0"];
                var enumValues = apiVersions.Select(a => (a, a));
                var inputEnum = InputFactory.StringEnum("ServiceVersion", enumValues, usage: InputModelTypeUsage.ApiVersionEnum);

                MockHelpers.LoadMockGenerator(
                    apiVersions: () => apiVersions,
                    inputEnums: () => [inputEnum]);
            }
            else
            {
                MockHelpers.LoadMockGenerator();
            }
        }

        [Test]
        public void TestImplements()
        {
            var client = InputFactory.Client("TestClient");
            var clientProvider = new ClientProvider(client);
            var clientOptionsProvider = new ClientOptionsProvider(client, clientProvider);

            Assert.IsNotNull(clientOptionsProvider);

            var implements = clientOptionsProvider.Implements;
            Assert.IsNotNull(implements);
            Assert.AreEqual(1, implements.Count);
            Assert.AreEqual(new CSharpType(typeof(ClientPipelineOptions)), implements[0]);
        }

        [TestCase(true, Category = ApiVersionsCategory)]
        [TestCase(false)]
        public void TestFields(bool containsApiVersions)
        {
            var client = InputFactory.Client("TestClient");
            var clientProvider = new ClientProvider(client);
            var clientOptionsProvider = new ClientOptionsProvider(client, clientProvider);

            Assert.IsNotNull(clientOptionsProvider);
            var fields = clientOptionsProvider.Fields;

            if (containsApiVersions)
            {
                Assert.AreEqual(1, fields.Count);
                Assert.IsTrue("LatestVersion" == fields[0].Name);
                Assert.IsTrue(fields[0].Type == clientOptionsProvider.NestedTypes[0].Type);
            }
            else
            {
                Assert.AreEqual(0, fields.Count);
            }
        }

        [TestCase(true, Category = ApiVersionsCategory)]
        [TestCase(false)]
        public void TestNestedTypes(bool containsApiVersions)
        {
            var client = InputFactory.Client("TestClient");
            var clientProvider = new ClientProvider(client);
            var clientOptionsProvider = new ClientOptionsProvider(client, clientProvider);

            Assert.IsNotNull(clientOptionsProvider);

            var nestedTypes = clientOptionsProvider.NestedTypes;
            if (containsApiVersions)
            {
                Assert.AreEqual(1, nestedTypes.Count);
                var nestedType = nestedTypes[0];
                Assert.IsTrue(nestedType.Name == "ServiceVersion");
                Assert.AreEqual(TypeSignatureModifiers.Public | TypeSignatureModifiers.Enum, nestedType.DeclarationModifiers);
                var nestedTypeFields = nestedType.Fields;
                Assert.AreEqual(2, nestedTypeFields.Count);
                Assert.AreEqual("V1_0", nestedTypeFields[0].Name);
                Assert.AreEqual("V2_0", nestedTypeFields[1].Name);
            }
            else
            {
                Assert.AreEqual(0, nestedTypes.Count);
            }
        }

        [TestCase(true, Category = ApiVersionsCategory)]
        [TestCase(false)]
        public void TestConstructors(bool containsApiVersions)
        {
            var client = InputFactory.Client("TestClient");
            var clientProvider = new ClientProvider(client);
            var clientOptionsProvider = new ClientOptionsProvider(client, clientProvider);

            Assert.IsNotNull(clientOptionsProvider);

            var ctors = clientOptionsProvider.Constructors;
            if (containsApiVersions)
            {
                Assert.AreEqual(1, ctors.Count);
                var ctor = ctors[0];
                var signature = ctor.Signature;
                Assert.AreEqual(1, signature.Parameters.Count);
                var versionParam = signature.Parameters[0];
                Assert.AreEqual("version", versionParam.Name);
                Assert.AreEqual(clientOptionsProvider.NestedTypes[0].Type, versionParam.Type);
                Assert.IsNotNull(versionParam.DefaultValue);
                Assert.IsNotNull(ctor.BodyStatements);
            }
            else
            {
                Assert.AreEqual(0, ctors.Count);
            }
        }

        [TestCase(true, Category = ApiVersionsCategory)]
        [TestCase(false)]
        public void TestProperties(bool containsApiVersions)
        {
            var client = InputFactory.Client("TestClient");
            var clientProvider = new ClientProvider(client);
            var clientOptionsProvider = new ClientOptionsProvider(client, clientProvider);

            Assert.IsNotNull(clientOptionsProvider);

            var properties = clientOptionsProvider.Properties;
            if (containsApiVersions)
            {
                Assert.AreEqual(1, properties.Count);
                var property = properties[0];
                Assert.AreEqual("Version", property.Name);
                Assert.AreEqual(new CSharpType(typeof(string)), property.Type);
                Assert.AreEqual(MethodSignatureModifiers.Internal, property.Modifiers);
                var body = property.Body;
                Assert.IsNotNull(body);
                var autoPropertyBody = body as AutoPropertyBody;
                Assert.IsNotNull(autoPropertyBody);
                Assert.IsFalse(autoPropertyBody?.HasSetter);
            }
            else
            {
                Assert.AreEqual(0, properties.Count);
            }
        }
    }
}
