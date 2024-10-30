package ai.gobots.authentication.application.user

import ai.gobots.authentication.domain.user.User

interface UserApplicationRepository {

    suspend fun findByEmail(email: String): User?

    suspend fun register(user: User): User
}
