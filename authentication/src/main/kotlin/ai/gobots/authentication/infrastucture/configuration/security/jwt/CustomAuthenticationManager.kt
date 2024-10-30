package ai.gobots.authentication.infrastucture.configuration.security.jwt

import ai.gobots.authentication.domain.exception.UnauthorizedException
import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
@Scope("prototype")
class CustomAuthenticationManager(
    private val jwtUtil: JWTUtil
): ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> =
        mono {
            validateAuthentication(authentication.credentials.toString())
        }

    private suspend fun validateAuthentication(authentication: String): CustomAuthenticationToken {
        return when {
            authentication.contains("Bearer ") -> {
                authenticate(authentication)
            }

            else -> {
                throw UnauthorizedException("No valid authentication provided")
            }
        }
    }

    private suspend fun authenticate(token: String): CustomAuthenticationToken {
        val claims = jwtUtil.getTokenBodyAsClaims(token)
        val roles = (claims["roles"] as List<*>)
            .filterIsInstance<Map<String, String>>()
            .mapNotNull { it["authority"] }

        return CustomAuthenticationToken(
            principal = claims.subject,
            credentials = token,
            authorities = roles
        ).apply { this.isAuthenticated = true }
    }
}