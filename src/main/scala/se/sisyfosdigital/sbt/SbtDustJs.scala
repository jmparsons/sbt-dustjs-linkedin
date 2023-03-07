package se.sisyfosdigital.sbt

import com.typesafe.sbt.jse.SbtJsTask
import sbt._
import com.typesafe.sbt.web.SbtWeb
import spray.json.{JsBoolean, JsObject}
import sbt.Keys._

object Import {

  object DustJsKeys {
    val dustjs = TaskKey[Seq[File]]("dustjs", "Invoke the DustJs compiler.")

    val helpers = SettingKey[Boolean]("dustjs-helpers", "Loads in DustJs helpers if they are available.")
    val infoNotice = SettingKey[Boolean]("dustjs-info-notice", "Show DustJs version number and if helpers are active.")
    val amdModule = SettingKey[Boolean]("dustjs-amd-module", "Compile the templates as AMD modules.")
  }

}

object SbtDustJs extends AutoPlugin {

  override def requires = SbtJsTask

  override def trigger = AllRequirements

  val autoImport = Import

  import SbtWeb.autoImport._
  import WebKeys._
  import SbtJsTask.autoImport.JsTaskKeys._
  import autoImport.DustJsKeys._

  val dustjsUnscopedSettings = Seq(
    includeFilter := "*.tl",
    jsOptions := JsObject(
      "helpers" -> JsBoolean(helpers.value),
      "infoNotice" -> JsBoolean(infoNotice.value),
      "amdModule" -> JsBoolean(amdModule.value)
    ).toString()
  )

  override def buildSettings = inTask(dustjs)(
    SbtJsTask.jsTaskSpecificUnscopedBuildSettings ++ Seq(
      moduleName := "dustjs",
      shellFile := getClass.getClassLoader.getResource("dust-shell.js")
    )
  )

  override def projectSettings = Seq(
    helpers := false,
    infoNotice := false,
    amdModule := false
  ) ++ inTask(dustjs)(
    SbtJsTask.jsTaskSpecificUnscopedProjectSettings ++
      inConfig(Assets)(dustjsUnscopedSettings) ++
      inConfig(TestAssets)(dustjsUnscopedSettings) ++
      Seq(
        Assets / taskMessage := "DustJs compiling",
        TestAssets / taskMessage := "DustJs test compiling"
      )
  ) ++ SbtJsTask.addJsSourceFileTasks(dustjs) ++ Seq(
    Assets / dustjs := (Assets / dustjs).dependsOn(Assets / webModules).value,
    TestAssets / dustjs := (TestAssets / dustjs).dependsOn(TestAssets / webModules).value
  )

}
