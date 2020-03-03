package com.example.otusfirstapp.Present.view

import android.app.Application
import com.example.otusfirstapp.Entity.ApiClient
import com.example.otusfirstapp.Entity.ApiInterface
import com.example.otusfirstapp.Entity.FilmInteractor

class OtusFirstApp : Application() {
    var service: ApiInterface? = null

    lateinit var filmService: FilmService
    lateinit var filmInteraror: FilmInteractor
    var filmRepositiry = FilmRepository()
    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
    }

    private fun initInterator () {
        filmInteraror = FilmInteractor(filmService, filmRepositiry)
    }

    private fun initRetrofit() {
        val retrofit = ApiClient.getClient()

        service = retrofit?.create(ApiInterface::class.java)
    }

    companion object {
        var instance: OtusFirstApp? = null
        private set
    }
}
