package ai.gobots.authentication.application.role

class RoleService(
    private val roleApplicationRepository: RoleApplicationRepository
) {

    suspend fun countByIdIn(roleIds: List<Long>): Long = roleApplicationRepository.countByIdIn(roleIds)
}
