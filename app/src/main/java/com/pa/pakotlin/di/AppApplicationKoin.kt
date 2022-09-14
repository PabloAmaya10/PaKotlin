package com.pa.pakotlin.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplicationKoin : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplicationKoin)
            modules(appModuleLogin, appModuleDashboard)
        }
    }
}
