package dev.lkeeeey.manager.business.core


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDTO(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("name") val name: String,
)
