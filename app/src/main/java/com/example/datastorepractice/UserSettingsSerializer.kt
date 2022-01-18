package com.example.datastorepractice

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

/**
 *  UserSettingsSerializer.kt.kt
 *
 *  Created by Hayeong Lee on 2022/01/18
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

object UserSettingsSerializer : Serializer<UserSettings> {
    override val defaultValue: UserSettings = UserSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettings {
        try {
            return UserSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserSettings, output: OutputStream) = t.writeTo(output)
}

val Context.userSettingsDataStore: DataStore<UserSettings> by dataStore(
    fileName = "userSettings.pb",
    serializer = UserSettingsSerializer
)
