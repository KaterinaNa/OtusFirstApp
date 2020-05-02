package com.example.otusfirstapp.presentation.view

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.R

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filmId = intent.getIntExtra("filmId", 0)
        Log.i(TAG, "filmId $filmId")

        if (filmId > 0) {
            val notificationManager = NotificationManagerCompat.from(this)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("filmId", filmId)
            val requestCode = 50
            val pendingIntent = PendingIntent.getActivity(
                this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT
            )

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_speaker_notes_24px)
                .setContentTitle("Вы собирались посмотреть фильм")
                .setContentText("FILM $filmId")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
            notificationManager.notify(filmId, builder.build())

        }
    }

    companion object {
        private const val TAG = "NotificationActivity"
        const val CHANNEL_ID = "filmAlarm"
    }
}
