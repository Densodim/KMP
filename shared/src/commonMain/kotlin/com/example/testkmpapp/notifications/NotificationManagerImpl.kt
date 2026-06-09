package com.example.testkmpapp.notifications

class NotificationManagerImpl(
    private val platformNotifier: PlatformNotifier
) : PushNotificationManager {
    private var notificationHandler: ((NotificationPayload) -> Unit)? = null

    override suspend fun registerForPushNotification(): PushNotificationResult {
        return try {
            PushNotificationResult.Success(platformNotifier.register())
        } catch (e: Exception) {
            PushNotificationResult.Failure(e)
        }
    }

    override suspend fun unregisterFromPushNotification() {
        platformNotifier.unregister()
    }

    override suspend fun getToken(): PushNotificationResult {
        return try {
            PushNotificationResult.Success(platformNotifier.getToken())
        } catch (e: Exception) {
            PushNotificationResult.Failure(e)
        }
    }

    override fun setNotificationHandler(handler: (NotificationPayload) -> Unit) {
        notificationHandler = handler
    }

    fun handlerNotification(payload: NotificationPayload) {
        notificationHandler?.invoke(payload)
    }
}