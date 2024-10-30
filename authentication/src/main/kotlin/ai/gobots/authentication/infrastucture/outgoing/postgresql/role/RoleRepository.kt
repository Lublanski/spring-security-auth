package ai.gobots.authentication.infrastucture.outgoing.postgresql.role

import ai.gobots.authentication.infrastucture.outgoing.postgresql.role.model.RoleEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface RoleRepository: ReactiveCrudRepository<RoleEntity, Long> {

    fun countByIdIn(roleIds: List<Long>): Mono<Long>
}
