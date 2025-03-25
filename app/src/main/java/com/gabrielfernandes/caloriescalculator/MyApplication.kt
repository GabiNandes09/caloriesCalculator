package com.gabrielfernandes.caloriescalculator

import android.app.Application
import com.gabrielfernandes.caloriescalculator.modules.appModule
import com.gabrielfernandes.caloriescalculator.modules.databaseModule
import com.gabrielfernandes.caloriescalculator.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, networkModule, databaseModule)
        }
    }
}