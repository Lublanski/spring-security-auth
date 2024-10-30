package ai.gobots.authentication.infrastucture.incoming.user.model

data class UserRepresentation(
    val id: Long? = null,
    val username: String,
    val email: String
)
