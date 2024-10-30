package ai.gobots.authentication.infrastucture.outgoing.postgresql.role.model

import ai.gobots.authentication.domain.role.Role
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("\"role\"")
class RoleEntity(
    @Id
    override var id: Long,
    name: String
): Role(
    id = id,
    name = name
)
