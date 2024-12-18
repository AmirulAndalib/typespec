import { Diagnostic, Namespace, Program } from "@typespec/compiler";
import { createTestHost, expectDiagnosticEmpty } from "@typespec/compiler/testing";
import { HttpTestLibrary } from "@typespec/http/testing";
import { OpenAPITestLibrary } from "@typespec/openapi/testing";
import assert from "node:assert";
import { convertOpenAPI3Document } from "../../../src/index.js";
import { OpenAPI3TestLibrary } from "../../../src/testing/index.js";
import {
  OpenAPI3Document,
  OpenAPI3Parameter,
  OpenAPI3PathItem,
  OpenAPI3Schema,
  Refable,
} from "../../../src/types.js";

function wrapCodeInTest(code: string): string {
  // Find the 1st namespace declaration and decorate it
  const serviceIndex = code.indexOf("@service");
  return `${code.slice(0, serviceIndex)}@test\n${code.slice(serviceIndex)}`;
}

export interface OpenAPI3Options {
  schemas?: Record<string, Refable<OpenAPI3Schema>>;
  parameters?: Record<string, Refable<OpenAPI3Parameter>>;
  paths?: Record<string, OpenAPI3PathItem>;
}

export async function tspForOpenAPI3(props: OpenAPI3Options) {
  const { namespace: TestService, diagnostics } = await compileForOpenAPI3(props);
  expectDiagnosticEmpty(diagnostics);
  return TestService;
}

export async function compileForOpenAPI3({ parameters, paths, schemas }: OpenAPI3Options): Promise<{
  namespace: Namespace;
  diagnostics: readonly Diagnostic[];
  program: Program;
}> {
  const openApi3Doc: OpenAPI3Document = {
    info: {
      title: "Test Service",
      version: "1.0.0",
    },
    openapi: "3.0.0",
    paths: { ...paths },
    components: {
      schemas: {
        ...(schemas as any),
      },
      parameters: {
        ...(parameters as any),
      },
    },
  };

  const code = await convertOpenAPI3Document(openApi3Doc);
  const testableCode = wrapCodeInTest(code);
  const host = await createTestHost({
    libraries: [HttpTestLibrary, OpenAPITestLibrary, OpenAPI3TestLibrary],
  });
  host.addTypeSpecFile("main.tsp", testableCode);

  const [types, diagnostics] = await host.compileAndDiagnose("main.tsp");
  const { TestService } = types;

  assert(
    TestService?.kind === "Namespace",
    `Expected TestService to be a namespace, instead got ${TestService?.kind}`,
  );
  return {
    namespace: TestService,
    diagnostics,
    program: host.program,
  };
}
