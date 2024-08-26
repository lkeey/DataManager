package dev.lkeeeey.manager.common

actual suspend fun Context.putData(
    key: String,
    `object`: String
) {
}

actual suspend fun Context.getData(key: String): String? {
    TODO("Not yet implemented")
}