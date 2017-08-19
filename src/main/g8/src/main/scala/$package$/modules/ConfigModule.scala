package $package$.modules

import akka.actor.ActorSystem
import akka.event.Logging
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods._
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import com.google.inject.{ AbstractModule, Provides, Singleton }
import $package$.config.{ Args, ArgsParser, Config }
import com.typesafe.config.{ ConfigFactory }
import net.codingwell.scalaguice.ScalaModule

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

class ConfigModule(args: Array[String]) extends AbstractModule with ScalaModule {
  override def configure() = {}

  @Provides
  @Singleton
  def provideArgs() = new ArgsParser().parse(args, Args()) match {
    case Some(arguments) ⇒ arguments
    case None            ⇒ throw new RuntimeException("Cannot parse arguments")
  }

  @Provides
  @Singleton
  def provideConfig(args: Args) = new Config {
    override def logLevel: String = args.logLevel
    override def serviceName: String = args.serviceName
    override def serviceHost: String = args.serviceHost
    override def servicePort: Int = args.servicePort
    override def shutdownAwaitTime: Duration = args.shutdownAwaitTime
    override def fileConfig: com.typesafe.config.Config = ConfigFactory.load(
      ConfigFactory.parseString(s"""akka{loglevel=\${args.logLevel}}""")
        .withFallback(ConfigFactory.load)
    )
    override def corsSettings: CorsSettings = CorsSettings.defaultSettings.copy(
      allowedMethods = List(HEAD, GET, POST, PUT, DELETE, OPTIONS)
    )
  }

  @Provides
  @Singleton
  def provideActorSystem(config: Config) = ActorSystem(s"\${config.serviceName}-actor", config.fileConfig)

  @Provides
  @Singleton
  def provideActorMaterializer(system: ActorSystem) = ActorMaterializer()(system)

  @Provides
  @Singleton
  def provideExecutionContext(system: ActorSystem) = system.dispatcher.asInstanceOf[ExecutionContext]

  @Provides
  @Singleton
  def provideLoggingAdapter(system: ActorSystem, config: Config) = Logging(system, s"\${config.serviceName}-log")
}
