package ai.gobots.authentication.infrastucture.configuration.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class TokenAuthenticationConverter: ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        val auth = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.first()
        return Mono.justOrEmpty(UsernamePasswordAuthenticationToken("", auth))
    }
}
