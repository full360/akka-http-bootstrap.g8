import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

import scalariform.formatter.preferences._

name := "$name$"
scalaVersion := "$scala_version$"
version := "0.1.0"
resolvers := Resolvers()
scalacOptions := Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:_",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-target:jvm-1.8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-unused",
  "-Ywarn-numeric-widen"
)

SbtScalariform.baseScalariformSettings ++ Seq(
  ScalariformKeys.preferences := ScalariformKeys.preferences.value
    .setPreference(RewriteArrowSymbols, true)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(DanglingCloseParenthesis, Force)
    .setPreference(FirstArgumentOnNewline, Force)
    .setPreference(FirstParameterOnNewline, Force)
)

libraryDependencies ++= {
  Seq(
    Library.akkaHttp,
    Library.akkaHttpCors,
    Library.akkaHttpSwagger,
    Library.scalaGuice,
    Library.akkaSlf4j,
    Library.logback,
    Library.scopt,
    Library.akkaHttpJson4s,
    Library.json4sNative,
    Library.json4sExt,
    Library.json4sJackson,
    TestLibrary.akkaHttpTestkit,
    TestLibrary.akkaTestkit,
    TestLibrary.scalaTest,
    TestLibrary.mockito
  )
}
