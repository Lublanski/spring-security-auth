package ai.gobots.authentication.infrastucture.configuration.security.jwt

import ai.gobots.authentication.infrastucture.configuration.security.TokenAuthenticationConverter
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher
import org.springframework.stereotype.Component

@Component
class JWTAuthenticationFilter(
    authenticationManager: CustomAuthenticationManager
): AuthenticationWebFilter(authenticationManager) {

    private val tokenAuthenticationConverter = TokenAuthenticationConverter()

    init {
        this.apply {
            setRequiresAuthenticationMatcher { exchange ->
                val path = exchange.request.path.toString()

                if (path.startsWith("/api/") && path != "/api/v1/users/register") {
                    ServerWebExchangeMatcher.MatchResult.match()
                } else {
                    ServerWebExchangeMatcher.MatchResult.notMatch()
                }
            }
            setServerAuthenticationConverter(tokenAuthenticationConverter)
        }
    }
}