package dev.lkeeeey.manager.business.core

import dev.lkeeeey.manager.common.Context
import dev.lkeeeey.manager.common.getData
import dev.lkeeeey.manager.common.putData


const val APP_DATASTORE = "com.razzaghi.shoppingbykmp"

class AppDataStoreManager(val context: Context) : AppDataStore {

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.putData(key, value)
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.getData(key)
    }
}