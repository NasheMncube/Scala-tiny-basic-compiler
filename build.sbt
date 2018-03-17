name := "scala-tiny-basic-compiler"

version := "1.0"

scalaVersion := "2.12.4"

scalacOptions += "-Ylog-classpath"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
