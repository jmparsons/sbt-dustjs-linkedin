name := "sbt-dustjs-linkedin"

organization := "com.jmparsons.sbt"

version := "1.0.5"

sbtPlugin := true

scalaVersion := "2.10.4"

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.1.3")

scriptedSettings

scriptedLaunchOpts += ("-Dproject.version=" + version.value)
