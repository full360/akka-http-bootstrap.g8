package $package$.server

import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, HttpResponse }
import akka.http.scaladsl.server.{ Directives, RejectionHandler }
import akka.stream.ActorMaterializer
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.google.inject.{ Inject, Singleton }
import $package$.config.Config
import $package$.server.model.{ Error, ErrorWrapper }
import $package$.server.util.JsonSupport
import $package$.services.health.HealthService
import $package$.services.swagger.SwaggerService

import scala.concurrent.{ Await, ExecutionContext }
import scala.util.{ Failure, Success }

@Singleton
class Server @Inject() (config: Config, swagger: SwaggerService, health: HealthService)(implicit log: LoggingAdapter, context: ExecutionContext, materializer: ActorMaterializer, system: ActorSystem) extends Directives with JsonSupport {

  def rejectionHandler = RejectionHandler.default
    .mapRejectionResponse {
      case res @ HttpResponse(_, _, ent: HttpEntity.Strict, _) ⇒
        val message = ent.data.utf8String.replaceAll("\"", """\"""")
        val payload = serialization.write(ErrorWrapper(Error(message)))
        res.copy(entity = HttpEntity(ContentTypes.`application/json`, payload))
      case x ⇒ x
    }

  def routes = handleRejections(rejectionHandler) {
    pathPrefix(config.serviceName) {
      cors(config.corsSettings) {
        swagger.routes ~ health.route
      }
    }
  }

  def start() = Http()
    .bindAndHandle(routes, config.serviceHost, config.servicePort)
    .onComplete({
      case Success(_)     ⇒ log.info(s"Listening on \${config.serviceHost}:\${config.servicePort}/\${config.serviceName}")
      case Failure(error) ⇒ log.error(error, "Cannot start server")
    })

  def shutdown() = {
    Await.result(Http().shutdownAllConnectionPools(), config.shutdownAwaitTime)
    materializer.shutdown()
    system.terminate().onComplete {
      case Success(_)     ⇒ log.info("Sucessfully Terminated")
      case Failure(error) ⇒ log.error(error, "Terminated with error")
    }
  }
}
