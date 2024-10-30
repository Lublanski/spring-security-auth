package ai.gobots.authentication.domain.exception

class UnauthorizedException(message: String?, cause: Throwable? = null, body: Any? = null) :
    ProjectException(
        message = message,
        cause = cause,
        status = HttpStatus.UNAUTHORIZED,
        body = body
    )
