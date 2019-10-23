package com.pessoadev.marvelapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pessoadev.marvelapp.data.model.Character
import com.pessoadev.marvelapp.util.Converters

@Database(entities = [Character::class], version = 6)
@TypeConverters(
    Converters.SeriesConverter::class,
    Converters.ComicsConverter::class,
    Converters.StoriesConverter::class,
    Converters.EventsConverter::class,
    Converters.ThumbnailConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}