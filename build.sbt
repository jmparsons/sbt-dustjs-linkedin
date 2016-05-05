name := "sbt-dustjs-linkedin"

organization := "com.jmparsons.sbt"

version := "1.0.4"

sbtPlugin := true

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.webjars" % "mkdirp" % "0.3.5",
  "org.webjars" % "dustjs-linkedin" % "2.7.2"
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.1.3")

scriptedSettings

scriptedLaunchOpts += ("-Dproject.version=" + version.value)