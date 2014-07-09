name := "sbt-dustjs-linkedin"

version := "1.0.2"

organization := "com.jmparsons.sbt"

scalaVersion := "2.10.4"

sbtPlugin := true

libraryDependencies ++= Seq(
  "org.webjars" % "mkdirp" % "0.3.5",
  "org.webjars" % "dustjs-linkedin" % "2.4.0-1"
)

resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  Resolver.mavenLocal
)

addSbtPlugin("com.typesafe.sbt" %% "sbt-js-engine" % "1.0.1")

publishMavenStyle := false

publishTo := {
  if (isSnapshot.value) Some(Classpaths.sbtPluginSnapshots)
  else Some(Classpaths.sbtPluginReleases)
}

scriptedSettings

scriptedLaunchOpts <+= version apply { v => s"-Dproject.version=$v" }

publishTo := Some(Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))

publishMavenStyle := false

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

crossPaths := false

pomExtra := (
  <url>http://github.com/jmparsons/sbt-dustjs-linkedin</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:jmparsons/sbt-dustjs-linkedin.git</url>
    <connection>scm:git:git@github.com:jmparsons/sbt-dustjs-linkedin.git</connection>
  </scm>
  <developers>
    <developer>
      <id>jmparsons</id>
      <name>Jonathan Parsons</name>
      <url>https://github.com/jmparsons</url>
    </developer>
  </developers>
)