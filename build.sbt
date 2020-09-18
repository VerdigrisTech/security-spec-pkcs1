import com.amazonaws.services.s3.model.Region

name := "spec-pkcs1"
organization := "co.verdigris"
version := "0.1.0"
scalaVersion := "2.10.5"
crossScalaVersions := Seq("2.10.5", "2.11.8")

lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.0"
lazy val apachecommons = "commons-codec" % "commons-codec" % "1.9"

libraryDependencies += scalatest % Test
libraryDependencies += apachecommons % Test

s3region := Region.US_Standard
s3overwrite := true
publishTo := Some(s3resolver.value("Verdigris Scala Libs", s3("scala-jars")))

resolvers += "Verdigris Scala Lib Repository" at "https://s3.amazonaws.com/scala-jars"
