package com.pessoadev.marvelapp

import android.app.Application
import androidx.multidex.MultiDex
import com.pessoadev.marvelapp.di.module.AppModule
import com.pessoadev.marvelapp.di.module.NetworkModule
import com.pessoadev.marvelapp.di.module.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule,dbModule))
        }

    }
}