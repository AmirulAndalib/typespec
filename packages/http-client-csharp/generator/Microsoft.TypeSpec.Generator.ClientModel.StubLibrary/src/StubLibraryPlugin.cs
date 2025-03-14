// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

using System.ComponentModel.Composition;

namespace Microsoft.TypeSpec.Generator.ClientModel.StubLibrary
{
    [Export(typeof(CodeModelPlugin))]
    [ExportMetadata("PluginName", nameof(StubLibraryPlugin))]
    public class StubLibraryPlugin : ScmCodeModelPlugin
    {
        [ImportingConstructor]
        public StubLibraryPlugin(GeneratorContext context) : base(context) { }

        public override void Configure()
        {
            base.Configure();
            AddVisitor(new StubLibraryVisitor());
        }
    }
}
