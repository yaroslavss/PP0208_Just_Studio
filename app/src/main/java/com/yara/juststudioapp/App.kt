package com.yara.juststudioapp

import android.app.Application
import com.yara.juststudioapp.di.AppComponent
import com.yara.juststudioapp.di.DaggerAppComponent

class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()

        // init App
        instance = this
        // create component
        dagger = DaggerAppComponent.factory().create(this)
    }

    companion object {
        // static
        lateinit var instance: App
            // private setter
            private set
    }
}