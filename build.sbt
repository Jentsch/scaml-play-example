name := "scaml-play-intro"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies += "org.scaml" %% "scaml" % "0.3.0.24"
resolvers += "ScaML Bintray Repo" at "https://bintray.com/artifact/download/jentsch/maven/"

libraryDependencies += "org.scala-stm" %% "scala-stm" % "0.7"
