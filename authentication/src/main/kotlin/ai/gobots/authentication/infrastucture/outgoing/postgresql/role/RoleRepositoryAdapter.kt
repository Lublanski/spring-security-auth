package ai.gobots.authentication.infrastucture.outgoing.postgresql.role

import ai.gobots.authentication.application.role.RoleApplicationRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component

@Component
class RoleRepositoryAdapter(
    private val roleRepository: RoleRepository
): RoleApplicationRepository {

    override suspend fun countByIdIn(roleIds: List<Long>): Long =
        roleRepository.countByIdIn(roleIds).awaitSingle()
}
