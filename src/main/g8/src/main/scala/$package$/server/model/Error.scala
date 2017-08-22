package $package$.server.model

case class Error(message: String)
case class ErrorWrapper(error: Error)
