name := "spec-pkcs1"
organization := "co.verdigris"
version := "0.1.0"
scalaVersion := "2.10.5"
crossScalaVersions := Seq("2.10.5", "2.11.8")

lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.0"
libraryDependencies += scalatest % Test