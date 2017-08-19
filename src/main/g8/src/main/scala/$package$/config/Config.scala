package $package$.config

import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings

import scala.concurrent.duration.Duration

abstract class Config {
  def logLevel: String
  def serviceName: String
  def serviceHost: String
  def servicePort: Int
  def shutdownAwaitTime: Duration
  def fileConfig: com.typesafe.config.Config
  def corsSettings: CorsSettings
}
