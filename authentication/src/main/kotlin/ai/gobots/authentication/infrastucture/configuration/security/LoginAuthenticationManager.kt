package ai.gobots.authentication.infrastucture.configuration.security

import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Scope
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
@Primary
class LoginAuthenticationManager(
    reactiveUserDetailsService: ReactiveUserDetailsService,
    passwordEncoder: PasswordEncoder
): UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService) {

    init {
        this.setPasswordEncoder(passwordEncoder)
    }
}
