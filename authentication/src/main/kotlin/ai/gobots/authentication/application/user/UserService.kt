package ai.gobots.authentication.application.user

import ai.gobots.authentication.application.role.RoleService
import ai.gobots.authentication.application.userrole.UserRoleService
import ai.gobots.authentication.domain.exception.BadRequestException
import ai.gobots.authentication.domain.exception.ConflictException
import ai.gobots.authentication.domain.exception.NotFoundException
import ai.gobots.authentication.domain.user.User
import ai.gobots.authentication.domain.user.UserRestRequest
import ai.gobots.authentication.domain.userrole.UserRole

class UserService(
    private val userApplicationRepository: UserApplicationRepository,
    private val userRoleService: UserRoleService,
    private val roleService: RoleService
) {

    suspend fun findByEmail(email: String): User =
        userApplicationRepository.findByEmail(email)
            ?: throw NotFoundException("User with email $email not found")

    suspend fun register(userRestRequest: UserRestRequest): User =
        try {
            findByEmail(userRestRequest.email).let {
                throw ConflictException("User with email ${it.email} already exists")
            }
        } catch (ex: NotFoundException) {
            validateIfAllPassedRolesExists(userRestRequest)

            userApplicationRepository.register(
                User(userRestRequest)
            ).also {
                createAllUserRole(it)
            }
        } catch (ex: Exception) {
            throw ex
        }

    private suspend fun createAllUserRole(createdUser: User) {
        roleService.countByIdIn(createdUser.roles)

        val userRolesToCreate = createdUser.roles.map { roleId ->
            UserRole(
                userId = createdUser.id!!,
                roleId = roleId
            )
        }

        userRoleService.createAll(userRolesToCreate)
    }

    private suspend fun validateIfAllPassedRolesExists(userRestRequest: UserRestRequest) {
        if (roleService.countByIdIn(userRestRequest.roles) != userRestRequest.roles.count().toLong()) {
            throw BadRequestException("There are some passed roles that not exists")
        }
    }
}
