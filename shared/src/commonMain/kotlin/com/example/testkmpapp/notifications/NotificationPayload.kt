package com.example.testkmpapp.notifications

data class NotificationPayload(
    val title: String,
    val body: String,
    val data: Map<String, String>
)

sealed class PushNotificationResult {
    data class Success(val token: String) : PushNotificationResult()
    data class Failure(val error: Exception) : PushNotificationResult()
}