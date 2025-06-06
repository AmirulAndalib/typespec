// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

using System.Linq;
using Microsoft.TypeSpec.Generator.Input;
using Microsoft.TypeSpec.Generator.Statements;
using Microsoft.TypeSpec.Generator.Tests.Common;
using NUnit.Framework;

namespace Microsoft.TypeSpec.Generator.ClientModel.Tests.Providers.RestClientProviders
{
    internal class MultipartFormDataTests
    {
        [Test]
        public void MultipartShouldUseContentTypeParamInCreateRequestMethod()
        {
            var operation = InputFactory.Operation("MultipartOperation", requestMediaTypes: ["multipart/form-data"], parameters: [InputFactory.ContentTypeParameter("multipart/form-data")]);
            var inputServiceMethod = InputFactory.BasicServiceMethod("Test", operation);
            var inputClient = InputFactory.Client("MultipartClient", methods: [inputServiceMethod]);
            MockHelpers.LoadMockGenerator(auth: () => new(new InputApiKeyAuth("mock", null), null), clients: () => [inputClient]);
            var client = ScmCodeModelGenerator.Instance.TypeFactory.CreateClient(inputClient);
            Assert.IsNotNull(client);
            var restClient = client!.RestClient;
            Assert.IsNotNull(restClient);
            var createMethod = restClient.Methods.FirstOrDefault(m => m.Signature.Name == "CreateMultipartOperationRequest");
            Assert.IsNotNull(createMethod);
            var statements = createMethod!.BodyStatements as MethodBodyStatements;
            Assert.IsNotNull(statements);
            Assert.IsTrue(statements!.Any(s => s.ToDisplayString() == "request.Headers.Set(\"Content-Type\", contentType);\n"));
        }
    }
}
