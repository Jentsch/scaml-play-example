name := "scaml-play-intro"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies += "org.scaml" %% "scaml" % "0.2.1.13"
libraryDependencies += "org.scala-stm" %% "scala-stm" % "0.7"

resolvers += "ScaML Bintray Repo" at "https://bintray.com/artifact/download/jentsch/maven/"

fork in run := true
