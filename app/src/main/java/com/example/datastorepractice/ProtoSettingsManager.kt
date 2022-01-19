package com.example.datastorepractice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

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
}
