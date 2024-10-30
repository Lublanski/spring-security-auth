package ai.gobots.authentication.infrastucture.configuration.application

import ai.gobots.authentication.application.role.RoleApplicationRepository
import ai.gobots.authentication.application.role.RoleService
import ai.gobots.authentication.application.user.UserApplicationRepository
import ai.gobots.authentication.application.user.UserService
import ai.gobots.authentication.application.userrole.UserRoleApplicationRepository
import ai.gobots.authentication.application.userrole.UserRoleService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfigurations {

    @Bean
    fun userRoleService(userRoleApplicationRepository: UserRoleApplicationRepository): UserRoleService =
        UserRoleService(userRoleApplicationRepository)

    @Bean
    fun roleService(roleApplicationRepository: RoleApplicationRepository): RoleService =
        RoleService(roleApplicationRepository)

    @Bean
    fun userService(
        userApplicationRepository: UserApplicationRepository,
        userRoleService: UserRoleService,
        roleService: RoleService
    ): UserService =
        UserService(
            userApplicationRepository,
            userRoleService,
            roleService
        )
}
