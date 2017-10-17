# sbt-dustjs-linkedin

[![Build Status](https://api.travis-ci.org/jmparsons/sbt-dustjs-linkedin.png?branch=master)](https://travis-ci.org/jmparsons/sbt-dustjs-linkedin)

An SBT plugin to compile [Dustjs](https://github.com/linkedin/dustjs) templates.

This plugin is a continuation of [play-dustjs][play-dustjs] built for [sbt-web][sbt-web] and [Play 2.3.x][play] -  [Play 2.6.x][play].

## Installation

Add the sbt plugin to your `project/plugins.sbt` file:

```scala
addSbtPlugin("com.jmparsons.sbt" % "sbt-dustjs-linkedin" % "1.0.6")
```

Two options are available:

Option              | Description
--------------------|------------------------------------------------------
helpers             | Loads in DustJs helpers if they are available.
infoNotice          | Show DustJs version number and if helpers are active.
amdModule           | Compile the templates as AMD modules. The templates will require the module 'dust.core' (as compiled by the dust template compile)

Example:

```scala
DustJsKeys.helpers in Assets := true
DustJsKeys.amdModule in Assets := true
```

## Usage

#### Install mkdirp, dustjs-linked and optionally dustjs-helpers for node

Add `package.json` to base folder of project and run `npm install` to load in node dependencies.

```json
{
  "name": "sbt-dust-tester",
  "version": "0.0.0",
  "dependencies": {
    "mkdirp": "~0.5.0",
    "dustjs-linkedin": "2.7.2",
    "dustjs-helpers": "1.7.3"
  }
}
```

Versions can be updated here, but should match the webjar bower versions.

#### Install dustjs-linked and optionally dustjs-helpers bower webjars

Include the corresponding versioned dust files as the node dependencies:

```scala
libraryDependencies ++= Seq(
  "org.webjars.bower" % "dustjs-linkedin" % "2.7.2",
  "org.webjars.bower" % "dustjs-helpers" % "1.7.3"
)
```

Newer versions can be added quickly using the add new webjar at [bower webjars](http://www.webjars.org/bower).

The dust files can also be added manually in the `public` folder from [LinkedIn Dustjs](http://linkedin.github.io/dustjs/).

#### Render your templates

Place your template **.tl** files anywhere inside of the `app/assets/` it will be available in the corresponding directory. If placed into `app/assets/templates/` the output would be `target/web/public/main/templates/`.

**RequireJS** example:

```coffeescript
require.config
  paths:
    jquery: "../lib/jquery/jquery"
    dust: "../lib/dustjs-linkedin/dist/dust-full"
    dustHelpers: "../lib/dustjs-helpers/dist/dust-helpers"
    testTemplate: "../templates/test"
  shim:
    jquery:
      exports: "$"
    dustHelpers:
      deps: ["dust"]
    testTemplate:
      deps: ["dust", "dustHelpers"]

require ["jquery", "testTemplate"], ->
  $ ->
    $.get "/data", (data) ->
      dust.render "templates/test", data, (err, out) ->
        $("#dust_pan").html (if err? then err else out)
```

**Javascript** example:

```javascript
<script src="@routes.Assets.at("lib/jquery/jquery.js")"></script>
<script src="@routes.Assets.at("lib/dustjs-linkedin/dist/dust-core.js")"></script>
<script src="@routes.Assets.at("lib/dustjs-helpers/dist/dust-helpers.js")"></script>
<script src="@routes.Assets.at("templates/test.js")"></script>
<script>
$(function() {
  $.get('@(routes.Application.data)', function(data) {
    console.log('data = ' + JSON.stringify(data));
    dust.render('templates/test', data, function(err, out) {
      $('#dust_pan').html(err ? err : out);
    });
  });
});
</script>
```

Example project with RequireJS using play-dustjs: <https://github.com/jmparsons/play-scala-backbone-todo>

#### Speed up compilation using node

Add this to your `build.sbt` to enabled node compilation - requires node installed on machine.

```scala
JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
```

## Changelog

1.0.5 - May 25, 2016

- Added template compiling as AMD module

1.0.4 - May 5, 2016

- Converted to using `package.json` for installing dependencies
- Versions can now be adjusted by updating `package.json`
- Updated readme example usage

1.0.3 - August 5, 2014

- Add Windows compatibility

1.0.2 - July 9, 2014

- Reverted away from transpiler to have more control
- Fixed source file mapping bug

1.0.1 - July 7, 2014

- Updated to dustjs-linked version 2.4.0
- Added webjar as npm module
- Removed dependency for npm installed dust

1.0.0 - May 19, 2014

- Inital commit
- Added tests using sbt scripted

## License
MIT: <http://jmparsons.mit-license.org> - [@jmparsons](http://twitter.com/jmparsons)

[play]: http://www.playframework.com/
[play-dustjs]: https://github.com/jmparsons/play-dustjs
[sbt-web]: https://github.com/sbt/sbt-web
[webjars]: http://www.webjars.org/
