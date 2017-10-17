import sbt.Resolver

name := "sbt-dustjs-linkedin"

organization := "com.jmparsons.sbt"

version := "1.0.6-SNAPSHOT"

resolvers ++= Seq(
	"Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
	Resolver.url("sbt snapshot plugins", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns),
	Resolver.sonatypeRepo("snapshots"),
	"Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"
)

sbtPlugin := true

addSbtJsEngine("1.2.2")
