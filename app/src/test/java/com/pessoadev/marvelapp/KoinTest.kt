package com.pessoadev.marvelapp

import android.app.Application
import com.pessoadev.marvelapp.di.module.AppModule
import com.pessoadev.marvelapp.di.module.NetworkModule
import com.pessoadev.marvelapp.di.module.dbModule
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class KoinTest : KoinTest {

    @Mock
    private lateinit var context: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun checkModules() {
        startKoin {
            androidContext(context)
            modules(listOf(AppModule, NetworkModule,dbModule))
        }.checkModules()
    }

}