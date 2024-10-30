package ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole

import ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole.model.UserRoleEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface UserRoleRepository: ReactiveCrudRepository<UserRoleEntity, Long> {

    fun findByUserId(userId: Long): Flux<UserRoleEntity>
}
