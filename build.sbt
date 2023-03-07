name := "sbt-dustjs-linkedin"
organization := "se.sisyfosdigital.sbt"
ThisBuild / description := "An SBT plugin to compile Dustjs templates."
ThisBuild / licenses += ("MIT", url("https://opensource.org/licenses/MIT"))

sbtPlugin := true
publishMavenStyle := false
bintrayRepository := "sbt-plugins"
bintrayOrganization := Some( "sisyfos-digital" )

addSbtJsEngine("1.2.3")
