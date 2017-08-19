package $package$.main

import com.google.inject.Guice
import $package$.modules.ConfigModule
import $package$.server.Server

object Main {

  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new ConfigModule(args))
    val server = injector.getInstance(classOf[Server])

    sys.addShutdownHook({
      server.shutdown()
    })

    server.start()
  }
}
