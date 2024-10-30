package ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole

import ai.gobots.authentication.application.userrole.UserRoleApplicationRepository
import ai.gobots.authentication.domain.userrole.UserRole
import ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole.model.UserRoleEntity
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component

@Component
class UserRoleRepositoryAdapter(
    private val userRoleRepository: UserRoleRepository
): UserRoleApplicationRepository {

    override suspend fun createAll(userRoles: List<UserRole>): List<UserRole> =
        userRoleRepository.saveAll(
            userRoles.map { UserRoleEntity(it) }
        ).collectList().awaitSingle()
}
