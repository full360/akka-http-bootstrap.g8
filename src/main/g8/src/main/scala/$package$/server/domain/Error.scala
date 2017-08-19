package $package$.server.domain

case class Error(message: String)
case class ErrorWrapper(error: Error)
