package ai.gobots.authentication.infrastucture.configuration.exception

import ai.gobots.authentication.domain.exception.ConflictException
import ai.gobots.authentication.domain.exception.NotFoundException
import ai.gobots.authentication.domain.exception.UnauthorizedException
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*

@Component
class GlobalErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes,
    resourceProperties: WebProperties.Resources,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(
    errorAttributes,
    resourceProperties,
    applicationContext
) {

    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> =
        RouterFunctions.route(RequestPredicates.all()) { request ->
            val error = getError(request)

            val statusCode = getStatusCode(error)

            val errorMessage = ErrorMessage(
                status = statusCode,
                detail = error.message
            )

            ServerResponse.status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorMessage))
        }

    private fun getStatusCode(error: Throwable): Int =
        when (error) {
            is UnauthorizedException -> HttpStatus.UNAUTHORIZED.value()

            is NotFoundException -> HttpStatus.NOT_FOUND.value()

            is ConflictException -> HttpStatus.CONFLICT.value()

            else -> HttpStatus.INTERNAL_SERVER_ERROR.value()
        }
}

