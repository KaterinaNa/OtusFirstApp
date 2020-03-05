package com.example.otusfirstapp.Present.view

import android.app.Application
import com.example.otusfirstapp.Entity.ApiClient
import com.example.otusfirstapp.Entity.FilmService
import com.example.otusfirstapp.Entity.FilmInteractor

class OtusFirstApp : Application() {
    var service: FilmService? = null

    lateinit var filmService: FilmService
    lateinit var filmInteractor: FilmInteractor
    var filmRepositiry = FilmRepository()
    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
    }

    private fun initInterator () {
        filmInteractor = FilmInteractor(filmService, filmRepositiry)
    }

    private fun initRetrofit() {
        val retrofit = ApiClient.getClient()

        service = retrofit?.create(FilmService::class.java)
    }

    companion object {
        var instance: OtusFirstApp? = null
        private set
    }
}
