package com.example.otusfirstapp

import android.app.Application
import com.example.otusfirstapp.data.Db
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.data.FilmRepository
import com.example.otusfirstapp.domain.FilmsUpdater
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OtusFirstApp : Application() {

    lateinit var filmService: FilmService
    lateinit var filmInteractor: FilmInteractor
    lateinit var filmsUpdater: FilmsUpdater


    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
        initInterator()
        initDb()
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
        filmsUpdater = FilmsUpdater(filmService)

    }

    private fun initDb() {
        Db.getInstance(this)?.getFilmDao()?.getAll()
    }

    companion object {
        lateinit var instance: OtusFirstApp
        private set
    }
}
