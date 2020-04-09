import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

scalaVersion := "2.13.1"

val Ver = new {
  val slinky = "0.6.4"
  val logback = "1.2.3"
  val circe = "0.13.0"
  val akka = "2.6.4"
}

lazy val sharedSettings = Seq(
  organization := "oen",
  scalaVersion := "2.13.1",
  version := "0.1.0-SNAPSHOT",
  // resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.1.1",
    "io.circe" %%% "circe-generic" % Ver.circe,
    "io.circe" %%% "circe-literal" % Ver.circe,
    "io.circe" %%% "circe-generic-extras" % Ver.circe,
    "io.circe" %%% "circe-parser" % "0.13.0",
    "com.softwaremill.quicklens" %%% "quicklens" % "1.5.0"
  ),
  scalacOptions ++= Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:higherKinds",
    "-Ymacro-annotations"
  )
)

lazy val jsSettings = Seq(
  libraryDependencies ++= Seq(
    "me.shadaj" %%% "slinky-web" % Ver.slinky,
    "me.shadaj" %%% "slinky-react-router" % Ver.slinky,
    "com.github.oen9" %%% "slinky-bridge-react-konva" % "0.1.1",
    "io.suzaku" %%% "diode" % "1.1.8"
  ),
  npmDependencies in Compile ++= Seq(
    "react" -> "16.13.0",
    "react-dom" -> "16.13.0",
    "react-popper" -> "1.3.7",
    "react-router-dom" -> "5.1.2",
    "path-to-regexp" -> "6.1.0",
    "bootstrap" -> "4.4.1",
    "jquery" -> "3.4.1",
    "konva" -> "4.2.2",
    "react-konva" -> "16.12.0-0",
    "use-image" -> "1.0.5"
  ),
  scalaJSUseMainModuleInitializer := true,
  version.in(webpack) := "4.42.1",
  webpackBundlingMode := BundlingMode.Application,
  webpackBundlingMode.in(fastOptJS) := BundlingMode.LibraryOnly()
)

lazy val jvmSettings = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "2.1.2",
    "com.typesafe.akka" %% "akka-http"   % "10.1.11",
    "com.typesafe.akka" %% "akka-stream" % Ver.akka,
    "com.typesafe.akka" %% "akka-actor" % Ver.akka,
    "de.heikoseeberger" %% "akka-http-circe" % "1.31.0",
    "ch.megard" %% "akka-http-cors" % "0.4.2",
    "com.typesafe.akka" %% "akka-slf4j" % Ver.akka,
    "ch.qos.logback" % "logback-classic" % Ver.logback
  ),
  target := baseDirectory.value / ".." / "target"
)

lazy val app =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full).in(file("."))
    .settings(sharedSettings)
    .jsSettings(jsSettings)
    .jvmSettings(jvmSettings)

lazy val appJS = app.js
  .enablePlugins(ScalaJSBundlerPlugin)
  .disablePlugins(RevolverPlugin)

lazy val appJVM = app.jvm
  .enablePlugins(JavaAppPackaging)
  .settings(
    dockerExposedPorts := Seq(8080),
    dockerBaseImage := "oracle/graalvm-ce:19.2.1",
    (unmanagedResourceDirectories in Compile) += (resourceDirectory in(appJS, Compile)).value,
    mappings.in(Universal) ++= webpack.in(Compile, fullOptJS).in(appJS, Compile).value.map { f =>
      f.data -> s"assets/${f.data.getName()}"
    },
    mappings.in(Universal) ++= Seq(
      (target in(appJS, Compile)).value / ("scala-" + scalaBinaryVersion.value) / "scalajs-bundler" / "main" / "node_modules" / "bootstrap" / "dist" / "css" / "bootstrap.min.css" -> "assets/bootstrap.min.css"
    ),
    bashScriptExtraDefines += """addJava "-Dassets=${app_home}/../assets""""
  )

disablePlugins(RevolverPlugin)
