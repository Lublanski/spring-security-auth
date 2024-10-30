package ai.gobots.authentication.infrastucture.outgoing.postgresql.userrole.model

import ai.gobots.authentication.domain.userrole.UserRole
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("user_role")
class UserRoleEntity(
    @Id
    override var id: Long? = null,
    userId: Long,
    roleId: Long
): UserRole(
    id = id,
    userId = userId,
    roleId = roleId
) {

    constructor(userRole: UserRole): this(
        id = userRole.id,
        userId = userRole.userId,
        roleId = userRole.roleId
    )
}
