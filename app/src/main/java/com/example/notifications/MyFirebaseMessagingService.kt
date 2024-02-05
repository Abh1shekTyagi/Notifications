package com.example.notifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.notifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val TAG = "MyFirebaseMsgService"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${message.from}")

        // TODO: Step 3.5 check messages for data
        // Check if message contains a data payload.
        message.data.let {
            Log.d(
                TAG, "Message data payload: ${
                    message.data.map {
                        it.value
                    }
                }"
            )
        }
        message.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            it.body?.let { it1 -> sendNotification(it1) }
        }

    }

    private fun sendNotification(messageBody: String) {
        val notificationManager =
            ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }
}