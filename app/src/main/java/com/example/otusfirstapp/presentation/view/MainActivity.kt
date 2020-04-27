package com.example.otusfirstapp.presentation.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

const val API_KEY = "836cbf0813244b3c64888bc53e1975f8"

class MainActivity : AppCompatActivity(),
    OnFilmClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FilmsListFragment(),
                    FilmsListFragment.TAG
                )
                .commit()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
            when (it.itemId) {
                R.id.bottom_navigator_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FilmsListFragment(),
                            FilmsListFragment.TAG
                        )
                        .commit()
                    true
                }
                R.id.bottom_navigator_fav -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FavoritesFragment(),
                            FavoritesFragment.TAG
                        )
                        .commit()
                    true
                }
                R.id.bottom_navigator_share -> {
                    onInvite()
                    true
                }
                else -> false
            }
        }

    }

    private fun onInvite() {
        //val intent = Intent(this, FavoritesFragment::class.java)
        // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val notificationManager = NotificationManagerCompat.from(this!!)

        val CHANNEL_ID = "channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel name" // getString(R.string.channel_name)
            val description = "Channel description" //getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel)
        }
// notificationId is a unique int for each notification that you must define
        val NOTIFICATION_ID = Date().time.toInt()
        val builder = NotificationCompat.Builder(this!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_speaker_notes_24px)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)
        notificationManager.notify(NOTIFICATION_ID, builder.build())


        return

        val textMessage = getString(R.string.share_into)
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.chooser)
        val chooser = Intent.createChooser(sendIntent, title)
        sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }

    override fun openFilmDetailed() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailsFragment(),
                DetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val bld: AlertDialog.Builder = AlertDialog.Builder(this)
            val no_lst =
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }
            val yes_lst =
                DialogInterface.OnClickListener { dialog, which ->
                    super.onBackPressed()
                }
            bld.setMessage(getString(R.string.exit_ask))
            bld.setTitle(getString(R.string.exit_ask_title))
            bld.setNegativeButton(getString(R.string.exit_no), no_lst)
            bld.setPositiveButton(getString(R.string.exit_yes), yes_lst)
            val dialog: AlertDialog = bld.create()
            dialog.show()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
