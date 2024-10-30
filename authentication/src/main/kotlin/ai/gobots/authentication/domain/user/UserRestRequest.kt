package ai.gobots.authentication.domain.user

data class UserRestRequest(
    val username: String,
    val email: String,
    val password: String,
    val roles: List<Long> = emptyList()
)
