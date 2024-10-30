package ai.gobots.authentication.domain.userrole

open class UserRole(
    open var id: Long? = null,
    val userId: Long,
    val roleId: Long
)
