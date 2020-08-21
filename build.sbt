name := "lot-parser"
version := "0.0.1"
organization := "ir.pr.saman"

scalaVersion := "2.12.8"
val json4sVersion = "3.6.5"

mainClass in(Compile, packageBin) := Some("ir.pr.saman.parser.html.Application")

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  //CONFIG
  "com.typesafe" % "config" % "1.4.0",
  // JSON
  "org.json4s" %% "json4s-native" % json4sVersion,
  //JSoup
  "org.jsoup" % "jsoup" % "1.13.1",
  //LOG
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  //TEST
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
