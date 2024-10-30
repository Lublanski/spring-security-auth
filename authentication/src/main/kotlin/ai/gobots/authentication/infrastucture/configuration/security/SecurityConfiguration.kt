package ai.gobots.authentication.infrastucture.configuration.security

import ai.gobots.authentication.infrastucture.configuration.security.jwt.JWTAuthenticationFilter
import ai.gobots.authentication.infrastucture.configuration.security.jwt.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfiguration(
    private val jwtLoginFilter: JWTLoginFilter,
    private val jwtAuthenticationFilter: JWTAuthenticationFilter
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors { it.disable() }
            .logout { it.disable() }
            .authorizeExchange { authorize ->
                authorize
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .pathMatchers(
                        "/actuator/**",
                        "/api/v1/users/register"
                    ).permitAll()
                    .anyExchange().authenticated()
            }
            .addFilterBefore(
                jwtLoginFilter,
                SecurityWebFiltersOrder.AUTHENTICATION
            )
            .addFilterBefore(
                jwtAuthenticationFilter,
                SecurityWebFiltersOrder.AUTHENTICATION
            )
            .build()

}
