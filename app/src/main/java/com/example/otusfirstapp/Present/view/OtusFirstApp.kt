package com.example.otusfirstapp.Present.view

import android.app.Application
import com.example.otusfirstapp.Entity.ApiClient
import com.example.otusfirstapp.Entity.FilmService
import com.example.otusfirstapp.Entity.FilmInteractor
import com.example.otusfirstapp.Entity.FilmRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OtusFirstApp : Application() {
    var service: FilmService? = null

    lateinit var filmService: FilmService
    lateinit var filmInteractor: FilmInteractor
    var filmRepository = FilmRepository()


    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
        initInterator()
    }

    private fun initInterator () {
        filmInteractor = FilmInteractor(filmService, filmRepository)
    }

    private fun initRetrofit() {

        var retrofit: Retrofit? = null

        service = retrofit?.create(FilmService::class.java)

        filmService) = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    }

    companion object {
        var instance: OtusFirstApp? = null
        private set
    }
}
