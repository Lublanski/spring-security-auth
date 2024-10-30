package ai.gobots.authentication.infrastucture.outgoing.postgresql.user

import ai.gobots.authentication.infrastucture.outgoing.postgresql.user.model.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: ReactiveCrudRepository<UserEntity, Long> {

    fun findByEmail(email: String): Mono<UserEntity>

    fun findByUsernameOrEmail(username: String, email: String): Mono<UserEntity>
}