package ai.gobots.authentication.domain.exception

abstract class ProjectException(
    override val message: String?,
    override val cause: Throwable?,
    val status: Int,
    val body: Any? = null
) : Exception(message, cause)