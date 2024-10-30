package ai.gobots.authentication.infrastucture.mapper

import ai.gobots.authentication.domain.user.User
import ai.gobots.authentication.infrastucture.incoming.user.model.UserRepresentation

object UserMapper {

    fun mapToUserRepresentation(user: User): UserRepresentation =
        user.let {
            UserRepresentation(
                id = it.id,
                username = it.username,
                email = it.email
            )
        }
}
