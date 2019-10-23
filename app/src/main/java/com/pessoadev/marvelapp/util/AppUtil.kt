package com.pessoadev.marvelapp.util

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.model.GlideUrl
import com.pessoadev.marvelapp.BuildConfig
import java.util.concurrent.TimeUnit


object AppUtil {

    fun loadImage(context: Context, url: String, image: AppCompatImageView) {
        GlideApp.with(context)
            .load(url)
            .into(image)
    }

    fun loadImageWithHeader(context: Context, url: String, image: AppCompatImageView) {
        val timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        val urlWithHeader = GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("apikey" , BuildConfig.API_PUBLIC)
                .addHeader("hash", HashGenerate.generate(timeStamp, BuildConfig.API_PRIVATE, BuildConfig.API_PUBLIC))
                .addHeader("ts" ,timeStamp.toString())
                .build()
        )

        GlideApp.with(context)
            .load(urlWithHeader)
            .into(image)
    }



    fun buildPathImage(path: String?, extension : String?) : String {
        return "$path.$extension"
    }


}