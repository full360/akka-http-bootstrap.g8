package $package$.services.health

import javax.ws.rs.Path

import akka.http.scaladsl.server.Directives
import com.google.inject.{ Inject, Singleton }
import $package$.services.health.model.Health
import $package$.server.util.JsonSupport
import io.swagger.annotations.{ Api, ApiOperation, ApiResponse, ApiResponses }

@Path("/health")
@Api(value = "/health", produces = "application/json")
class HealthService @Inject() extends Directives with JsonSupport {

  @ApiOperation(value = "Check the application status", httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 200, message = "Shows that the API is working correctly")
  ))
  @Singleton
  def route =
    path("health") {
      get {
        complete(Health("UP"))
      }
    }
}
