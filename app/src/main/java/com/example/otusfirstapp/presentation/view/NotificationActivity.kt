package com.example.otusfirstapp.presentation.view

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film

/*vclass NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.setExtrasClassLoader(Film::class.java.classLoader)
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
            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_speaker_notes_24px)
                .setContentTitle("Вы собирались посмотреть фильм")
                .setContentText(film.name)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentIntent(pendingIntent)
            notificationManager.notify(notificationId, builder.build())
        }
    }

    companion object {
        private const val TAG = "NotificationActivity"
        const val CHANNEL_ID = "filmAlarm"
    }
}*/
