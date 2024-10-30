package ai.gobots.authentication.domain.exception

class BadRequestException(message: String?, cause: Throwable? = null, body: Any? = null) :
    ProjectException(
        message = message,
        cause = cause,
        status = HttpStatus.BAD_REQUEST,
        body = body
    )