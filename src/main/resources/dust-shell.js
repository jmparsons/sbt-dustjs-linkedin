/*global process, require */

(function () {

    "use strict";

    var args = process.argv,
        fs = require("fs"),
        mkdirp = require("mkdirp"),
        path = require("path"),
        dust = require("dustjs-linkedin");

    var SOURCE_FILE_MAPPINGS_ARG = 2;
    var TARGET_ARG = 3;
    var OPTIONS_ARG = 4;

    var sourceFileMappings = JSON.parse(args[SOURCE_FILE_MAPPINGS_ARG]);
    var target = args[TARGET_ARG];
    var options = JSON.parse(args[OPTIONS_ARG]);

    var sourcesToProcess = sourceFileMappings.length;
    var results = [];
    var problems = [];

    function compileDone() {
        if (--sourcesToProcess === 0) {
            console.log("\u0010" + JSON.stringify({results: results, problems: problems}));
        }
    }

    function throwIfErr(e) {
        if (e) throw e;
    }

    sourceFileMappings.forEach(function (sourceFileMapping) {

        var input = sourceFileMapping[0];
        var outputFile = sourceFileMapping[1].replace(/\.tl$/i, ".js");
        var output = path.join(target, outputFile);
        var moduleName = outputFile.replace(/\.js$/i, '');

        fs.readFile(input, "utf8", function (e, contents) {
            throwIfErr(e);

            try {
                mkdirp(path.dirname(output), function (e) {
                    throwIfErr(e);

                    var compiled = dust.compile(contents, moduleName);

                    fs.writeFile(output, compiled, "utf8", function (e) {
                        throwIfErr(e);

                        results.push({
                            source: input,
                            result: {
                                filesRead: [input],
                                filesWritten: [output]
                            }
                        });

                        compileDone();
                    });
                });

            } catch (err) {
                problems.push({
                    message: err.message,
                    severity: "error",
                    lineNumber: err.location.first_line,
                    characterOffset: err.location.first_column,
                    lineContent: contents.split("\n")[err.location.first_line],
                    source: input
                });
                results.push({
                    source: input,
                    result: null
                });

                compileDone();
            }
        });
    });
})();
