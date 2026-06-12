package com.example.testkmpapp

import android.app.Application
import com.example.testkmpapp.di.initKoin
import com.example.testkmpapp.notifications.NotificationManagerImpl
import com.example.testkmpapp.notifications.PlatformNotifierAndroid
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestKMPApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        val firebaseApp = FirebaseApp.initializeApp(this)

        val notificationManager = NotificationManagerImpl(PlatformNotifierAndroid())

        notificationManager.setNotificationHandler { payload ->
            // TODO: handle push payload.
        }

        if (firebaseApp == null) {
            // Firebase requires google-services.json or explicit FirebaseOptions.
            // Until it is configured, do not call notificationManager.registerForPushNotification().
        }
    }
}
