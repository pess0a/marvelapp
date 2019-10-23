package com.pessoadev.marvelapp.di.module

import androidx.room.Room
import com.pessoadev.marvelapp.data.source.local.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "marvel-database"
        ).fallbackToDestructiveMigration()
            .build()
    }
    factory { get<AppDatabase>().characterDao() }
}
