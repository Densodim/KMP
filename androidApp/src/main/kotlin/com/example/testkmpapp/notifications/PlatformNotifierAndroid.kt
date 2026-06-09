package com.example.testkmpapp.notifications

import com.google.firebase.messaging.FirebaseMessaging
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

class PlatformNotifierAndroid : PlatformNotifier {
    private val firebaseMessaging: FirebaseMessaging by lazy {
        FirebaseMessaging.getInstance()
    }
    private var token: String = ""

    override suspend fun register(): String {
        token = firebaseMessaging.awaitToken()
        return token
    }

    override fun unregister() {
        firebaseMessaging.deleteToken()
        token = ""
    }

    override fun getToken(): String = token

    private suspend fun FirebaseMessaging.awaitToken(): String =
        suspendCancellableCoroutine { continuation ->
            token
                .addOnSuccessListener { token -> continuation.resume(token) }
                .addOnFailureListener { error -> continuation.resumeWithException(error) }
        }
}
