name := "spec-pkcs1"
organization := "co.verdigris"
version := "0.1.0"
scalaVersion := "2.10.5"
crossScalaVersions := Seq("2.10.5", "2.11.8")

lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.0"
lazy val apachecommons = "commons-codec" % "commons-codec" % "1.9"
libraryDependencies += scalatest % Test
libraryDependencies += apachecommons % Test