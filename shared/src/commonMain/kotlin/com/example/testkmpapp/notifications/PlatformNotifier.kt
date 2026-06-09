package com.example.testkmpapp.notifications

interface PlatformNotifier {
    suspend fun register(): String

    fun unregister()

    fun getToken(): String
}