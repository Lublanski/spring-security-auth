package ai.gobots.authentication.infrastucture.outgoing.postgresql.user

import ai.gobots.authentication.application.user.UserApplicationRepository
import ai.gobots.authentication.infrastucture.outgoing.postgresql.user.model.UserEntity
import kotlinx.coroutines.reactive.awaitFirst
import ai.gobots.authentication.domain.user.User
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserApplicationRepository {

    override suspend fun findByEmail(email: String): User? =
        userRepository.findByEmail(email).awaitFirstOrNull()

    override suspend fun register(user: User): User =
        userRepository.save(
            UserEntity(user, passwordEncoder.encode(user.password))
        ).awaitFirst()
}
