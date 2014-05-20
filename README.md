# sbt-dustjs-linkedin

[![Build Status](https://api.travis-ci.org/jmparsons/sbt-dustjs-linkedin.png?branch=master)](https://travis-ci.org/jmparsons/sbt-dustjs-linkedin)

An SBT plugin to compile [Dustjs](https://github.com/linkedin/dustjs) templates.

This plugin is a continuation of [play-dustjs][play-dustjs] built for [sbt-web][sbt-web] and [Play 2.3.x][play].

## Installation

Add the sbt plugin to your `project/plugins.sbt` file:

    addSbtPlugin("com.jmparsons.sbt" % "sbt-dustjs-linkedin" % "1.0.0")

The plugin requires the node [dustjs-linkedin][dustjs-linkedin] plugin either installed locally or globally.

## Usage

Include the core dust file - can be downloaded from [LinkedIn Dustjs](http://linkedin.github.io/dustjs/) or [here](https://github.com/linkedin/dustjs/tree/master/dist).

    <script src="@routes.Assets.at("javascripts/dust-core-2.3.4.js")"></script>

Or can be used with [webjars][webjars]:

    libraryDependencies ++= Seq(
      "org.webjars" % "dustjs-linkedin" % "2.3.4"
    )

The webjars path will look like this:

    <script src="@routes.Assets.at("lib/dustjs-linkedin/dust-core.js")"></script>

Be sure your webjar or front end version of `dustjs-linkedin` matches with the node compiler version.

If you're specifiying the npm install using a package.json file:

    {
      "name": "test-project",
      "version": "1.0.0",
      "dependencies": {},
      "devDependencies": {
        "dustjs-linkedin": "~2.3.4"
      },
      "engines": {
        "node": ">=0.8.0"
      }
    }

Place your template **.tl** files anywhere inside of the `app/assets/` it will be available in the corresponding directory. If placed into `app/assets/templates/` the output would be `target/web/public/main/templates/`.

Pull in the generated javascript template file:

    <script src="@routes.Assets.at("templates/example.js")"></script>

Example requirejs loading config:

    require.config
      paths:
        dust: "../lib/dustjs-linkedin/dust-core"
      shim:
        "../templates/example":
          deps: ["dust"]

    require ["dust", "../templates/example"], () ->

Render the template:

    $(function() {
      $.get('@routes.Application.data', function(data) {
        console.log('data = ' + JSON.stringify(data));
        dust.render('example', data, function(err, out) {
          $('#dust_pan').html(err ? err : out);
        });
      });
    });

Example project with RequireJS using play-dustjs: <https://github.com/jmparsons/play-scala-backbone-todo>

## Changelog

1.0.0 - May 19, 2014

- Inital commit
- Added tests using sbt scripted

[play]: http://www.playframework.com/
[play-dustjs]: https://github.com/jmparsons/play-dustjs
[sbt-web]: https://github.com/sbt/sbt-web
[webjars]: http://www.webjars.org/
[dustjs-linkedin]: https://www.npmjs.org/package/dustjs-linkedin

## License
MIT: <http://jmparsons.mit-license.org> - [@jmparsons](http://twitter.com/jmparsons)