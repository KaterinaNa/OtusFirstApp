package com.example.otusfirstapp.Entity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class FilmInteractor(private val filmService: FilmService, private val filmRepository: FilmRepository) {

    fun getRepos(id: Int, apiKey: String, callback: GetFilmCallback) {
        filmService.getFilmInfo(id, apiKey).enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    filmRepository.addToCache(listOf(response.body()!!))

                    callback.onSuccess(filmRepository.cachedOrFakeRepos)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetFilmCallback {
        fun onSuccess(repos: List<Film>)
        fun onError(error: String)
    }
}