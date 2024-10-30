package ai.gobots.authentication.infrastucture.configuration.security

import ai.gobots.authentication.infrastucture.configuration.security.jwt.Credentials
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class LoginRequestAuthenticationConverter: ServerAuthenticationConverter {
    private val objectMapper = jacksonObjectMapper()
    override fun convert(exchange: ServerWebExchange): Mono<Authentication> =
        exchange.request.body
            .next()
            .flatMap { buffer ->
                val request = objectMapper.readValue<Credentials>(buffer.asInputStream())
                Mono.just(request)
            }
            .map { request ->
                UsernamePasswordAuthenticationToken(request.username, request.password)
            }
}
