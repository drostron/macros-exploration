import sbt._
import Keys._

object Build extends Build {

  lazy val packageSettings = Defaults.defaultSettings ++ Seq[Setting[_]](
    organization := "me.theillusionof",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.0",
    scalacOptions := Seq(
      "-feature",
      "-deprecation",
      "-encoding", "utf8",
      "-Xlog-free-terms"),
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % "2.10.0",
      "org.scala-lang" % "scala-compiler" % "2.10.0"),
    resolvers ++= Seq(
      "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"))

  lazy val root = Project(
    id = "macros-exploration",
    base = file("."),
    settings = packageSettings,
    dependencies = Seq(macros))

  lazy val macros = Project(
    id = "macros-exploration-defs",
    base = file("macros"),
    settings = packageSettings)
}