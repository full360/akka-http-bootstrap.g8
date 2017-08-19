package $package$.services.health

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, StatusCodes }
import akka.http.scaladsl.testkit.ScalatestRouteTest
import $package$.BaseSpec

class HealthServiceSpec extends BaseSpec with ScalatestRouteTest {

  "The health service" should {
    val svc = new HealthService

    "have a route for checking the health" which {
      "returns 200 if the service is healthy" in {
        Get("/health") ~> svc.route ~> check {
          status shouldBe StatusCodes.OK
        }
      }

      "returns correct response if the service is healthy" in {
        val response = HttpEntity(ContentTypes.`application/json`, """{"status":"UP"}""")

        Get("/health") ~> svc.route ~> check {
          responseEntity shouldBe response
        }
      }
    }
  }
}
