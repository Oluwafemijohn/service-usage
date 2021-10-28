package com.example.intentseervice

import android.app.Application

class App : Application() {
        companion object {
                lateinit var appInstance:App
        fun app():Application {
            return appInstance
        }
    }
//    companion object {
//
//        lateinit var INSTANCE: App
//
//    }

    private lateinit var appInstance: App
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

}