import sbt._

object Version {
  final val akkaHttp = "10.0.9"
  final val json4s = "3.5.0"
}

object Library {
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Version.akkaHttp
  val akkaHttpCors = "ch.megard" %% "akka-http-cors" % "0.2.1"
  val akkaHttpSwagger = "com.github.swagger-akka-http" %% "swagger-akka-http" % "0.10.0"
  val scalaGuice = "net.codingwell" %% "scala-guice" % "4.1.0"
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % "2.4.19"
  val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val scopt = "com.github.scopt" %% "scopt" % "3.6.0"
  val akkaHttpJson4s = "de.heikoseeberger" %% "akka-http-json4s" % "1.17.0"
  val json4sNative = "org.json4s" %% "json4s-native" % Version.json4s
  val json4sExt = "org.json4s" %% "json4s-ext" % Version.json4s
  val json4sJackson = "org.json4s" %% "json4s-jackson" % Version.json4s
}

object TestLibrary {
  val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % Version.akkaHttp % "test"
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  val mockito = "org.mockito" % "mockito-core" % "2.8.47" % "test"
}
