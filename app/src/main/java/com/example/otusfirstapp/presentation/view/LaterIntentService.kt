package com.example.otusfirstapp.presentation.view

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film

class LaterIntentService : IntentService("LaterIntentService") {
    override fun onHandleIntent(intent: Intent?) {
        intent!!.setExtrasClassLoader(Film::class.java.classLoader)
        val bundle = intent.getBundleExtra("bundle")
        val film = bundle?.getParcelable<Film>("film")
        Log.i(TAG, "film $film")

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
                .setContentTitle("Вы собирались посмотреть фильм")
                .setContentText(film.name)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            notificationManager.notify(notificationId, builder.build())
        }
    }


    override fun onCreate() {
        Log.i(TAG, "onCreateIntend")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommandIntend")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroyIntend")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "LaterIntentService"
        const val CHANNEL_ID = "filmAlarm"
    }


    init {
        Log.d(TAG, "<Init>")
    }
}