package ai.gobots.authentication.application.role

interface RoleApplicationRepository {

    suspend fun countByIdIn(roleIds: List<Long>): Long
}
