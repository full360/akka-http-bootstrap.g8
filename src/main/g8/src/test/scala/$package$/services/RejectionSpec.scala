package $package$.services

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{ Directives, Route }
import akka.http.scaladsl.testkit.ScalatestRouteTest
import $package$.BaseSpec

class RejectionSpec extends BaseSpec with ScalatestRouteTest with Directives {
  "The application" should {
    "reject requests" which {
      val route =
        Route.seal(
          path("hello") {
            get {
              complete("Hello there")
            }
          }
        )

      "cannot be found" in {
        Get("/notfound") ~> route ~> check {
          status shouldBe StatusCodes.NotFound
        }
      }

      "use incorrect method" in {
        Post("/hello") ~> route ~> check {
          status shouldBe StatusCodes.MethodNotAllowed
        }
      }
    }
  }
}
