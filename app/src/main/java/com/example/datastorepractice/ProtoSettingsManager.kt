package com.example.datastorepractice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

/**
 *  ProtoSettingsManager.kt.kt
 *
 *  Created by Hayeong Lee on 2022/01/19
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

class ProtoSettingsManager(private val context: Context) {
    private val Context.protoDataStore: DataStore<UserSettings> by dataStore(
        fileName = "user_settings.pb",
        serializer = ProtoSettingsSerializer
    )

    // 읽기
    fun getColor(): Flow<UserSettings> {
        return context.protoDataStore.data
            .catch { ex ->
                if (ex is IOException) {
                    emit(UserSettings.getDefaultInstance())
                } else {
                    throw ex
                }
            }
    }

    // 쓰기
    suspend fun updateColor(bgColor: UserSettings.BgColor) {
        context.protoDataStore.updateData { userSettings ->
            userSettings.toBuilder()
                .setBgColor(bgColor)
                .build()
        }
    }
}
