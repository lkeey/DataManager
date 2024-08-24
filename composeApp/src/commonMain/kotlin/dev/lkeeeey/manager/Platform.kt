package dev.lkeeeey.manager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform