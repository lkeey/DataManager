package dev.lkeeeey.manager.business.services

import dev.lkeeeey.manager.business.core.MainGenericResponse


interface SplashService {

    companion object {
        const val REGISTER = "register"
        const val LOGIN = "login"
    }

    suspend fun login(email: String, password: String): MainGenericResponse<String?>

    suspend fun register(name: String, email: String, password: String): MainGenericResponse<String?>

}
