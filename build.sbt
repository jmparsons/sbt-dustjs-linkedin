import sbt.Resolver

name := "sbt-dustjs-linkedin"
organization := "se.sisyfosdigital.sbt"
description in ThisBuild := "An SBT plugin to compile Dustjs templates."
licenses in ThisBuild += ("MIT", url("https://opensource.org/licenses/MIT"))

resolvers ++= Seq(
	"Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
	Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
	Resolver.sonatypeRepo("snapshots"),
	"Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
)

sbtPlugin := true
publishMavenStyle := false
bintrayRepository := "sbt-plugins"
bintrayOrganization := None

addSbtJsEngine("1.2.2")
