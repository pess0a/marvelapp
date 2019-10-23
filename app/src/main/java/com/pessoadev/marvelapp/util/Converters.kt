package com.pessoadev.marvelapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pessoadev.marvelapp.data.model.*

object Converters  {

    class SeriesConverter {
        @TypeConverter
        fun toObject(json: String): Series {
            val type = object : TypeToken<Series>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Series): String {
            val type = object: TypeToken<Series>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class StoriesConverter {
        @TypeConverter
        fun toObject(json: String): Stories {
            val type = object : TypeToken<Stories>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Stories): String {
            val type = object: TypeToken<Stories>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class ThumbnailConverter {
        @TypeConverter
        fun toObject(json: String): Thumbnail {
            val type = object : TypeToken<Thumbnail>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Thumbnail): String {
            val type = object: TypeToken<Thumbnail>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class ComicsConverter {
        @TypeConverter
        fun toObject(json: String): Comics {
            val type = object : TypeToken<Comics>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Comics): String {
            val type = object: TypeToken<Comics>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class EventsConverter {
        @TypeConverter
        fun toObject(json: String): Events {
            val type = object : TypeToken<Events>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: Events): String {
            val type = object: TypeToken<Events>() {}.type
            return Gson().toJson(obj, type)
        }
    }

    class UrlConverter {
        @TypeConverter
        fun toObject(json: String): ArrayList<Url> {
            val type = object : TypeToken<ArrayList<Url>>() {}.type
            return Gson().fromJson(json, type)
        }

        @TypeConverter
        fun toJson(obj: ArrayList<Url>): String {
            val type = object: TypeToken<ArrayList<Url>>() {}.type
            return Gson().toJson(obj, type)
        }
    }
}