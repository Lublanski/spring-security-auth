package ai.gobots.authentication.infrastucture.configuration.security.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class CustomAuthenticationToken(
    private val principal: String,
    private val credentials: String,
    authorities: List<String>
): AbstractAuthenticationToken(authorities.map { GrantedAuthority { it } }) {
    override fun getCredentials(): String = principal

    override fun getPrincipal(): String = credentials
}
