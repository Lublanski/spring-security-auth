package ai.gobots.authentication.infrastucture.configuration.security

import ai.gobots.authentication.domain.exception.NotFoundException
import ai.gobots.authentication.infrastucture.outgoing.postgresql.role.RoleRepository
import ai.gobots.authentication.infrastucture.outgoing.postgresql.user.UserRepository
import ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole.UserRoleRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ReactiveUserDetailsService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val userRoleRepository: UserRoleRepository
): ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> =
        userRepository.findByUsernameOrEmail(username, username)
            .switchIfEmpty(Mono.error(NotFoundException("User $username not found")))
            .flatMap { savedUser ->
                userRoleRepository.findByUserId(savedUser.id!!)
                    .map { it.roleId }
                    .collectList()
                    .flatMap { roleIds ->
                        roleRepository.findAllById(roleIds)
                            .map { role -> SimpleGrantedAuthority(role.name) }
                            .collectList()
                            .map { authorities ->
                                User.withUsername(savedUser.username)
                                    .password(savedUser.password)
                                    .authorities(authorities)
                                    .build()
                            }
                    }
            }
}
