// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

/* eslint no-console: "off" */

import fs from "node:fs/promises";
import path from "node:path";

const GENERATED_DEFS = path.resolve("generated-defs");

const HELPER_DECLARATION_PATH = path.resolve(GENERATED_DEFS, "helpers");
const HELPER_SRC_PATH = path.resolve("src", "helpers");

async function* visitAllFiles(base: string): AsyncIterable<string> {
  const contents = await fs.readdir(base, { withFileTypes: true });

  for (const entry of contents) {
    if (entry.isDirectory()) {
      yield* visitAllFiles(path.join(base, entry.name));
    } else if (entry.isFile()) {
      yield path.join(base, entry.name);
    }
  }
}

async function buildPackageJsonTs() {
  console.log("Building package.json.ts");
  const packageJson = await fs.readFile(path.resolve("package.json"), "utf-8");

  const parsed = JSON.parse(packageJson);

  const mergedDependencies: Record<string, string> = {
    ...parsed.devDependencies,
    ...parsed.dependencies,
  };

  const fileText = [
    "// Copyright (c) Microsoft Corporation",
    "// Licensed under the MIT license.",
    "",
    "// prettier-ignore",
    "",
    `export const hsjsDependencies: Record<string, string> = {`,
    ...Object.entries(mergedDependencies).map(([name, version]) => {
      return `  ${JSON.stringify(name)}: ${JSON.stringify(version)},`;
    }),
    "};",
    "",
  ].join("\n");

  await fs.writeFile(path.resolve(GENERATED_DEFS, "package.json.ts"), fileText);
}

async function main() {
  console.log("Building JS server generator helpers.");

  await buildPackageJsonTs();

  const allFiles: string[] = [];
  const indices = new Map<string, string[]>();

  const ctxPath = path.resolve("src", "ctx.js");

  await fs.rm(HELPER_DECLARATION_PATH, { recursive: true, force: true });

  function addIndex(dir: string, file: string) {
    const index = indices.get(dir);

    if (index) {
      index.push(file);
    } else {
      indices.set(dir, [file]);
    }
  }

  for await (const file of visitAllFiles(HELPER_SRC_PATH)) {
    allFiles.push(file);
    addIndex(path.dirname(file), file);
  }

  for (const file of allFiles) {
    if (!file.endsWith(".ts")) {
      continue;
    }

    const relativePath = path.relative(HELPER_SRC_PATH, file);

    console.log("Building helper:", relativePath);

    const targetPath = path.resolve(HELPER_DECLARATION_PATH, relativePath);

    const targetDir = path.dirname(targetPath);
    const targetFileBase = path.basename(targetPath, ".ts");
    const isIndex = targetFileBase === "index";
    const targetBase = isIndex ? path.basename(targetDir) : targetFileBase;
    await fs.mkdir(targetDir, { recursive: true });

    const childModules = isIndex ? indices.get(path.dirname(file)) : [];

    if (isIndex) {
      indices.delete(path.dirname(file));
    }

    const contents = await fs.readFile(file, "utf-8");

    let childModuleLines =
      childModules?.map((child) => {
        const childBase = path.basename(child, ".ts");
        return `  await import("./${childBase}.js").then((m) => m.createModule(module));`;
      }) ?? [];

    if (childModuleLines.length > 0) {
      childModuleLines = ["      // Child modules", ...childModuleLines, ""];
    }

    const transformed = [
      "// Copyright (c) Microsoft Corporation",
      "// Licensed under the MIT license.",
      "",
      `import { Module } from "${path.relative(targetDir, ctxPath).replace(/\\/g, "/")}";`,
      "",
      "export let module: Module = undefined as any;",
      "",
      "// prettier-ignore",
      "const lines = [",
      ...contents.split(/\r?\n/).map((line) => "  " + JSON.stringify(line) + ","),
      "];",
      "",
      "export async function createModule(parent: Module): Promise<Module> {",
      "  if (module) return module;",
      "",
      "  module = {",
      `    name: ${JSON.stringify(targetBase)},`,
      `    cursor: parent.cursor.enter(${JSON.stringify(targetBase)}),`,
      "    imports: [],",
      "    declarations: [],",
      "  };",
      "",
      ...childModuleLines,
      "  module.declarations.push(lines);",
      "",
      "  parent.declarations.push(module);",
      "",
      "  return module;",
      "}",
      "",
    ].join("\n");

    await fs.writeFile(targetPath, transformed);
  }

  console.log("Building index files.");

  for (const [dir, files] of indices.entries()) {
    console.log("Building index:", dir);

    const relativePath = path.relative(HELPER_SRC_PATH, dir);

    const targetPath = path.resolve(HELPER_DECLARATION_PATH, relativePath, "index.ts");

    const childIndices = new Set(
      allFiles
        .filter((f) => f.startsWith(dir))
        // Remove the directory prefix and then check if it's a nested directory lower.
        .map((f) => /^[/\\]([a-zA-Z_-]+)[/\\]/.exec(f.replace(dir, "")))
        .filter((match) => !!match)
        .map((match) => match[1]),
    );

    const children = [
      ...[...childIndices].map((dir) => {
        return `  await import("./${dir}/index.js").then((m) => m.createModule(module));`;
      }),

      ...files.map((file) => {
        return `  await import("./${path.basename(file, ".ts")}.js").then((m) => m.createModule(module));`;
      }),
    ];

    const transformed = [
      "// Copyright (c) Microsoft Corporation",
      "// Licensed under the MIT license.",
      "",
      `import { Module } from "${path.relative(path.dirname(targetPath), ctxPath).replace(/\\/g, "/")}";`,
      "",
      "export let module: Module = undefined as any;",
      "",
      "export async function createModule(parent: Module): Promise<Module> {",
      "  if (module) return module;",
      "",
      "  module = {",
      `    name: ${JSON.stringify(path.basename(dir))},`,
      `    cursor: parent.cursor.enter(${JSON.stringify(path.basename(dir))}),`,
      "    imports: [],",
      "    declarations: [],",
      "  };",
      "",
      "  // Child modules",
      ...children,
      "",
      "  parent.declarations.push(module);",
      "",
      "  return module;",
      "}",
      "",
    ].join("\n");

    await fs.writeFile(targetPath, transformed);
  }
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
