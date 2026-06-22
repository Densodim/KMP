package com.example.testkmpapp.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
object Coder {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun <T> encode(serializer: KSerializer<T>, value: T): String {
        val jsonStr = json.encodeToString(serializer, value)
        return Base64.UrlSafe.encode(jsonStr.encodeToByteArray())
    }

    fun <T> decode(serializer: KSerializer<T>, value: String): T {
        val decodedByteArray = Base64.UrlSafe.decode(value)
        val decodedStr = decodedByteArray.decodeToString()
        return json.decodeFromString(serializer, decodedStr)
    }
}
