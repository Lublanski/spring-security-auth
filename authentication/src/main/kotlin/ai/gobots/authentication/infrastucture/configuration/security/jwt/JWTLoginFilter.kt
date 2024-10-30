package ai.gobots.authentication.infrastucture.configuration.security.jwt

import ai.gobots.authentication.infrastucture.configuration.security.LoginAuthenticationManager
import ai.gobots.authentication.infrastucture.configuration.security.LoginRequestAuthenticationConverter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JWTLoginFilter(
    private val jwtUtil: JWTUtil,
    authenticationManager: LoginAuthenticationManager
): AuthenticationWebFilter(authenticationManager) {

    private val loginRequestAuthenticationConverter = LoginRequestAuthenticationConverter()

    init {
        this.setServerAuthenticationConverter(loginRequestAuthenticationConverter)
        this.setRequiresAuthenticationMatcher {
            ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login").matches(it)
        }
    }

    override fun onAuthenticationSuccess(
        authentication: Authentication,
        webFilterExchange: WebFilterExchange
    ): Mono<Void> {
        val jwtToken = jwtUtil.generateToken(authentication.name, authentication.authorities)
        webFilterExchange.exchange.response.headers.add(HttpHeaders.AUTHORIZATION, "Bearer $jwtToken")

        return webFilterExchange.exchange.response.setComplete()
    }
}
