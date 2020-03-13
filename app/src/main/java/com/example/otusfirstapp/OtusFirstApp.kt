package com.example.otusfirstapp

import android.app.Application
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.data.FilmRepository
import com.example.otusfirstapp.domain.FilmsUpdater
import com.example.otusfirstapp.data.entity.Db
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class OtusFirstApp : Application() {

    lateinit var filmService: FilmService
    lateinit var filmInteractor: FilmInteractor
    lateinit var filmsUpdater: FilmsUpdater


    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
        initInterator()

        Executors.newSingleThreadScheduledExecutor().execute(
            Runnable {
                Db.getInstance(this)?.getFilmDao()?.getAll()
            }
    }

    private fun initInterator () {
        val filmRepository = FilmRepository()

        filmInteractor = FilmInteractor(
            filmService,
            filmRepository
        )
    }

    private fun initRetrofit() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        filmService = retrofit.create(FilmService::class.java)
        filmsUpdater = FilmsUpdater(filmService)

    }

    companion object {
        lateinit var instance: OtusFirstApp
        private set
    }
}
