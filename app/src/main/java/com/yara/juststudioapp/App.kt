package com.yara.juststudioapp

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // init App
        instance = this
    }

    companion object {
        // static
        lateinit var instance: App
            // private setter
            private set
    }
}