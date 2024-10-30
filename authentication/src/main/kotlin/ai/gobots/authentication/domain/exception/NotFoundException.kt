package ai.gobots.authentication.domain.exception

class NotFoundException(message: String?, cause: Throwable? = null, body: Any? = null) :
    ProjectException(
        message = message,
        cause = cause,
        status = HttpStatus.NOT_FOUND,
        body = body
    )
