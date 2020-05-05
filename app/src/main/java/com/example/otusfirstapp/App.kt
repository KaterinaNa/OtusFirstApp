package com.example.otusfirstapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.example.otusfirstapp.data.AppDb
import com.example.otusfirstapp.data.Db
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.data.FilmRepository
import com.example.otusfirstapp.presentation.view.LaterIntentService

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var filmService: FilmService
    lateinit var filmInteractor: FilmInteractor
    lateinit var sharedPref: SharedPreferences
    lateinit var db: AppDb

    private val APP_PREFERENCES = "mysettings"

    override fun onCreate() {
        super.onCreate()

        instance = this
        initRetrofit()
        initInterator()
        initDb()
        createNotificationChannel()
        sharedPref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    private fun initInterator () {
        val filmRepository = FilmRepository()

        filmInteractor = FilmInteractor(
            filmService,
            filmRepository
        )
    }

    private fun initRetrofit() {
        val gson = GsonBuilder()
            .create()

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        filmService = retrofit.create(FilmService::class.java)

    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = NotificationManagerCompat.from(this)
            val name = "Вы хотели посмотреть"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(LaterIntentService.CHANNEL_ID, name, importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun initDb() {
        db = Db.getInstance(this)!!
    }

    companion object {
        lateinit var instance: App
    }
}
