package ai.gobots.authentication.domain.user

open class User(
    open var id: Long? = null,
    val email: String,
    val username: String,
    val password: String,
    val roles: List<Long> = emptyList()
) {

    constructor(user: UserRestRequest) : this(
        email = user.email,
        username = user.username,
        password = user.password,
        roles = user.roles
    )
}
