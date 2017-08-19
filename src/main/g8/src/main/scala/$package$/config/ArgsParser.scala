package $package$.config

import scala.concurrent.duration.Duration

class ArgsParser extends scopt.OptionParser[Args]("service-parser") {
  val default = Args()

  // Help
  help("help")
    .text("Print this usage text")

  // Log
  opt[String]("log-level")
    .valueName("<level>")
    .action((str, opt) ⇒ opt.copy(logLevel = str.toUpperCase))
    .text(s"Log level. Default is '\${default.logLevel}'")

  // Service
  opt[String]("service-name")
    .valueName("<name>")
    .action((str, opt) ⇒ opt.copy(serviceName = str))
    .text(s"Name of the service (used for base path). Default is '\${default.serviceName}'")
  opt[String]("service-host")
    .valueName("<host>")
    .action((str, opt) ⇒ opt.copy(serviceHost = str))
    .text(s"Host of the service. Default is '\${default.serviceHost}'")
  opt[Int]("service-port")
    .valueName("<port>")
    .action((value, opt) ⇒ opt.copy(servicePort = value))
    .text(s"Port of the service. Default is '\${default.servicePort}'")
  opt[Duration]("shutdown-await-time")
    .valueName("<shutdown-await-time>")
    .action((dur, opt) ⇒ opt.copy(shutdownAwaitTime = dur))
    .text(s"Shutdown phase await time '\${default.shutdownAwaitTime}'")
}
