package com.example.datastorepractice

import android.app.Application

/**
 *  SampleApplication.kt
 *
 *  Created by Hayeong Lee on 2022/01/20
 *  cc. https://kangmin1012.tistory.com/47
 */

class SampleApplication : Application() {

    private lateinit var protoSettingsManager: ProtoSettingsManager

    companion object {
        private lateinit var sampleApplication: SampleApplication
        fun getInstance(): SampleApplication = sampleApplication
    }

    override fun onCreate() {
        super.onCreate()
        sampleApplication = this
        protoSettingsManager = ProtoSettingsManager(this)
    }

    fun getProtoSettingManager(): ProtoSettingsManager = protoSettingsManager
}
