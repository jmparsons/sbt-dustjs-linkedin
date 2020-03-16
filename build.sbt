name := "sbt-dustjs-linkedin"
organization := "se.sisyfosdigital.sbt"
description in ThisBuild := "An SBT plugin to compile Dustjs templates."
licenses in ThisBuild += ("MIT", url("https://opensource.org/licenses/MIT"))

sbtPlugin := true
publishMavenStyle := false
bintrayRepository := "sbt-plugins"
bintrayOrganization := Some( "sisyfos-digital" )

addSbtJsEngine("1.2.3")
