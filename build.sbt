name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies += "org.scex" %% "scex" % "0.2.1.30"

resolvers += "Scex Bintray Repo" at "https://bintray.com/artifact/download/jentsch/maven/"
