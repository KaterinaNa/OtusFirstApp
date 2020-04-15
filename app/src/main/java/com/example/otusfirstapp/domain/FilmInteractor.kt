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
        val dateNow = Date().time
        val dateResponse = OtusFirstApp.instance.sharedPref.getLong(
            LAST_RESPONSE_KEY, dateNow + PERIOD
        )
//        val timePeriod = dateResponse-dateNow
//        Log.i(TAG, timePeriod.toString())
//        if (true || timePeriod >= PERIOD) {
            filmService.getTopRatedMovies(apiKey, page).enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                    if (response.isSuccessful) {
                        val films = response.body()?.results
                        Log.i(TAG, response.body()?.totalResults.toString())
                        if (films == null) {
                            callback.onError("API returned null results")
                            return
                        }
                        filmRepository.addToCache(films)

//                        val editor = OtusFirstApp.instance.sharedPref.edit()
//                        editor.putLong(LAST_RESPONSE_KEY, Date().time)
//                        editor.apply()

                        callback.onSuccess(getFilms())
                    } else {
                        callback.onError(response.code().toString() + "")
                    }
                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                    callback.onError("Network error probably...")
                }
            })
//        } else {
//            callback.onSuccess(getFilms())
//        }
    }

    fun getFilms(): ArrayList<Film> {
        return filmRepository.cachedOrFakeFilms
    }

    fun clearFilms() {
        filmRepository.clearCache()
    }

    fun getFilmById(id: Int): Film {
        return filmRepository.cachedOrFakeFilms[id]
    }

    companion object {
        private const val TAG = "FilmInteractor"
        private const val LAST_RESPONSE_KEY = "lastResponse"
        private const val PERIOD = 20*60*1000
    }
}


interface GetTopFilmsCallback {
    fun onSuccess(films: ArrayList<Film>)
    fun onError(error: String)
}