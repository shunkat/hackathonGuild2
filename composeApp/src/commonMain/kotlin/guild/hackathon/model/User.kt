package guild.hackathon.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Int?,
    val name: String?,
    val email: String?,
    val createdAt: String?
)