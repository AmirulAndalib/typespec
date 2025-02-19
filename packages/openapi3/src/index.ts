export const namespace = "TypeSpec.OpenAPI";

export { convertOpenAPI3Document } from "./cli/actions/convert/convert.js";
export * from "./decorators.js";
export { $lib } from "./lib.js";
export * from "./openapi.js";

export * from "./types.js";

/** @internal */
export { $decorators } from "./tsp-index.js";
