package ai.gobots.authentication.infrastucture.configuration.security.jwt

import ai.gobots.authentication.domain.exception.UnauthorizedException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtil(
    @Value("\${jwt.secretKey}")
    private val secretKey: String
) {

    private val expirationMillis: Long = 10 * 60 * 1000

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String =
        Jwts.builder()
            .subject(username)
            .claim("roles", authorities)
            .expiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(
                Keys.hmacShaKeyFor(secretKey.toByteArray())
            )
            .compact()

    suspend fun getTokenBodyAsClaims(token: String): Claims =
        try {
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseSignedClaims(getTokenFormatted(token))
                .payload
        } catch (ex: Exception) {
            throw UnauthorizedException(ex.message)
        }


    private suspend fun getTokenFormatted(token: String): String =
        if (token.contains("Bearer ")) {
            token.substring(7, token.length)
        } else {
            token
        }
}
