import sbt.Resolver

lazy val root = Project("plugins", file(".")).dependsOn(plugin)

lazy val plugin = RootProject(file("../").getCanonicalFile.toURI)

resolvers += Resolver.sbtPluginRepo("snapshots")
