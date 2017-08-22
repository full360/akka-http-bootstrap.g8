package $package$.server.util

import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s
import org.json4s.DefaultFormats
import org.json4s.ext.JodaTimeSerializers

trait JsonSupport extends Json4sSupport {
  implicit val formats = DefaultFormats ++ JodaTimeSerializers.all
  implicit val serialization = json4s.jackson.Serialization
}

