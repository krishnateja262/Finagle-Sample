name := "Finagle-Sample"

version := "1.0"

scalaVersion := "2.11.6"

enablePlugins(JavaAppPackaging)

packageDescription in Debian := "Voodoo"

maintainer in Debian := "Krishna"

libraryDependencies += "io.undertow" % "undertow-core" % "1.2.6.Final"

libraryDependencies += "io.undertow" % "undertow-servlet" % "1.2.6.Final"

libraryDependencies += "io.undertow" % "undertow-websockets-jsr" % "1.2.6.Final"

libraryDependencies += "com.mashape.unirest" % "unirest-java" % "1.4.5"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.6"

libraryDependencies += "org.apache.httpcomponents" % "httpasyncclient" % "4.0.2"

libraryDependencies += "org.apache.httpcomponents" % "httpmime" % "4.3.6"

libraryDependencies += "org.json" % "json" % "20140107"

libraryDependencies += "org.jsoup" % "jsoup" % "1.8.2"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3.1"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

libraryDependencies += "commons-io" % "commons-io" % "2.4"
