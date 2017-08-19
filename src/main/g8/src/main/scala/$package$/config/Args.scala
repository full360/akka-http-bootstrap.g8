package $package$.config

import scala.concurrent.duration._

final case class Args(
    logLevel:          String   = "INFO",
    serviceName:       String   = "service",
    serviceHost:       String   = "127.0.0.1",
    servicePort:       Int      = 8888,
    shutdownAwaitTime: Duration = 5.second
)

