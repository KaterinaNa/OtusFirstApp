package com.example.otusfirstapp.domain

import android.util.Log
import com.example.otusfirstapp.OtusFirstApp
import com.example.otusfirstapp.data.FilmRepository
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class FilmInteractor(private val filmService: FilmService, private val filmRepository: FilmRepository) {

    fun getTopFilms(apiKey: String, page: Int, callback: GetTopFilmsCallback) {

        val apiCallback = GetTopRatedCallback(callback, filmRepository)

        filmService.getTopRatedMovies(apiKey, page).enqueue(apiCallback)
    }

    fun getFilms(): ArrayList<Film> {
        return filmRepository.cachedOrFakeFilms
    }

    fun getFilmById(id: Int): Film {
        return filmRepository.cachedOrFakeFilms[id]
    }
}


interface GetTopFilmsCallback {
    fun onSuccess(films: ArrayList<Film>)
    fun onError(error: String)
}

class GetTopRatedCallback(val callback: GetTopFilmsCallback, val filmRepository: FilmRepository) : Callback<FilmsResponse> {
    override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
        if (response.isSuccessful) {
            val films = response.body()?.results
            Log.i("Interactor", response.body()?.totalResults.toString())
            if (films == null) {
                callback.onError("API returned null results")
                return
            }
            filmRepository.addToCache(films)

            val dateResponse = Date()
            val editor = OtusFirstApp.instance.sharedPref.edit()
            editor.putLong(OtusFirstApp.instance.LAST_RESPONSE_KEY, dateResponse.time)
            editor.apply()

            callback.onSuccess(filmRepository.cachedOrFakeFilms)
        } else {
            callback.onError(response.code().toString() + "")
        }
    }

    override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
        callback.onError("Network error probably...")
    }
}