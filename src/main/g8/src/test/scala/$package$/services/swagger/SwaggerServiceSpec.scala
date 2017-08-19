package $package$.services.swagger

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import $package$.BaseSpec
import $package$.config.Config

class SwaggerServiceSpec extends BaseSpec with ScalatestRouteTest {
  "The swagger service" should {
    val config = mock[Config]
    val svc = new SwaggerService(config)

    "expose the docs through a route" which {
      "returns 200 if it's mounted" in {
        Get("/docs/api/swagger.json") ~> svc.routes ~> check {
          status shouldBe StatusCodes.OK
        }
      }
    }
  }
}
