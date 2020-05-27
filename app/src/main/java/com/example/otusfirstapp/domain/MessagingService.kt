package com.example.otusfirstapp.domain

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.App
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.view.LaterIntentService
import com.example.otusfirstapp.presentation.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MessagingService: FirebaseMessagingService() {

    init {
        Log.i(TAG, "init")
    }

    override fun onNewToken(token: String) {
        Log.i(TAG, "Refreshed token: $token")
        // sendRegistrationToServer(token)
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        for ((key, value) in remoteMessage.data) {
            Log.i(TAG, "key-value $key $value")
        }


        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
         Log.i(TAG, "From: " + remoteMessage.from)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
          Log.i(TAG, "Message data payload: " + remoteMessage.data)

        }


        if (remoteMessage.notification != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        }


        val filmId = remoteMessage.data.get("filmId")
        if (filmId == null) return
        val film = App.instance.filmInteractor.getFilmById(filmId.toInt())

        if (film != null) {
            val notificationManager = NotificationManagerCompat.from(this)

            val intent = Intent(this, MainActivity::class.java)
            intent.setExtrasClassLoader(Film::class.java.classLoader)
            intent.putExtra("film", film)
            val requestCode = 50
            val pendingIntent = PendingIntent.getActivity(
                this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT
            )

            val notificationId = film.id
            val builder = NotificationCompat.Builder(this, LaterIntentService.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_speaker_notes_24px)
                .setContentTitle(remoteMessage.notification!!.title)
                .setContentText(remoteMessage.notification!!.body)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            notificationManager.notify(notificationId, builder.build())
        }
    }

    companion object {
        const val TAG = "MessagingService"
    }
}
