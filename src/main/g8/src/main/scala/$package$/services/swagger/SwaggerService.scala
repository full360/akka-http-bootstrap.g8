package $package$.services.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import com.google.inject.Inject
import $package$.config.Config
import $package$.services.health.HealthService

class SwaggerService @Inject() (config: Config) extends SwaggerHttpService {
  override val apiClasses = Set(classOf[HealthService])
  override val host = s"\${config.serviceHost}:\${config.servicePort}"
  override val basePath = s"\${config.serviceName}"
  override val apiDocsPath = "/docs/api"
  override val info = Info(version = "1.0", title = s"\${config.serviceName}", description = s"\${config.serviceName}")
}
