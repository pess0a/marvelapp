package com.pessoadev.marvelapp.util

import java.security.MessageDigest

object HashGenerate {

    fun generate(timestamp: Long, privateKey: String, publicKey: String): String {
        return (timestamp.toString() + privateKey + publicKey).toMD5()
    }

    private fun String.toMD5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digested = md.digest(toByteArray())
        return digested.joinToString("") { String.format("%02x", it) }
    }
}