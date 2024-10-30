package ai.gobots.authentication.domain.exception

class ConflictException(message: String?, cause: Throwable? = null, body: Any? = null) :
    ProjectException(
        message = message,
        cause = cause,
        status = HttpStatus.CONFLICT,
        body = body
    )