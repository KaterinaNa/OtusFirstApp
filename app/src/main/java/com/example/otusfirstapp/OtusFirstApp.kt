package com.example.otusfirstapp

import android.app.Application
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.data.FilmRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OtusFirstApp : Application() {

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

    }

    companion object {
        var instance: OtusFirstApp? = null
        private set
    }
}
