package ai.gobots.authentication.infrastucture.incoming.user

import ai.gobots.authentication.application.user.UserService
import ai.gobots.authentication.domain.user.UserRestRequest
import ai.gobots.authentication.infrastucture.incoming.user.model.UserRepresentation
import ai.gobots.authentication.infrastucture.mapper.UserMapper
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserRestAdapter(
    private val userService: UserService
) {

    @PostMapping("/register")
    suspend fun register(@RequestBody user: UserRestRequest): UserRepresentation =
        UserMapper.mapToUserRepresentation(
            userService.register(user)
        )

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test")
    suspend fun test(): String = "Hello World"
}
