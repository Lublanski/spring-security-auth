package ai.gobots.authentication.infrastucture.outgoing.postgresql.user.model

import ai.gobots.authentication.domain.user.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("\"user\"")
class UserEntity(
    @Id
    override var id: Long? = null,
    email: String,
    username: String,
    password: String,
    roles: List<Long>
): User(
    id = id,
    email = email,
    username = username,
    password = password,
    roles = roles
) {

    constructor(user: User, encodedPassword: String) : this(
        id = user.id,
        email = user.email,
        username = user.username,
        password = encodedPassword,
        roles = user.roles
    )
}
