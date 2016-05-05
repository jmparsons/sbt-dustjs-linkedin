name := "sbt-dustjs-linkedin"

organization := "com.jmparsons.sbt"

version := "1.0.4"

sbtPlugin := true

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.webjars" % "mkdirp" % "0.5.0",
  "org.webjars" % "dustjs-linkedin" % "2.4.0-1"
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.1.3")

scriptedSettings

scriptedLaunchOpts += ("-Dproject.version=" + version.value)