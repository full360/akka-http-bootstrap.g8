package $package$.server.models

case class Error(message: String)
case class ErrorWrapper(error: Error)
