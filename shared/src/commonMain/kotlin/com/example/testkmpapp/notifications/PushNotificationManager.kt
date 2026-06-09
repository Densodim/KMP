package com.example.testkmpapp.notifications

interface PushNotificationManager {
    suspend fun registerForPushNotification(): PushNotificationResult
    suspend fun unregisterFromPushNotification()
    suspend fun getToken(): PushNotificationResult
    fun setNotificationHandler(handler: (NotificationPayload) -> Unit)
}