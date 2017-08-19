import sbt._

object Resolvers {
  def apply() = Seq(
    "jcenter" at "http://jcenter.bintray.com",
    "confluent" at "http://packages.confluent.io/maven/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    Resolver.bintrayRepo("hseeberger", "maven")
  )
}

