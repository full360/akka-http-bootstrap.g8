package $package$

import org.scalatest.{ Matchers, WordSpecLike }
import org.scalatest.mockito.MockitoSugar

trait BaseSpec extends WordSpecLike with MockitoSugar with Matchers {}
