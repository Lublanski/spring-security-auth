package ai.gobots.authentication.application.userrole

import ai.gobots.authentication.domain.userrole.UserRole

class UserRoleService(
    private val userRoleApplicationRepository: UserRoleApplicationRepository
) {

    suspend fun createAll(userRoles: List<UserRole>): List<UserRole> =
        userRoleApplicationRepository.createAll(userRoles)


}
