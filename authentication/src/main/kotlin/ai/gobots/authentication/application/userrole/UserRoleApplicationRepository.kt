package ai.gobots.authentication.application.userrole

import ai.gobots.authentication.domain.userrole.UserRole

interface UserRoleApplicationRepository {

    suspend fun createAll(userRoles: List<UserRole>): List<UserRole>
}
