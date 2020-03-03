package com.example.otusfirstapp.Entity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmInteractor(private val filmService: FilmService, private val filmRepository: FilmRepository) {

    fun getRepos(username: String, callback: GetFilmCallback) {
        filmService.getUserRepos(username).enqueue(object : Callback<List<Film>> {
            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                if (response.isSuccessful) {
                    filmRepository.addToCache(response.body()!!)

                    callback.onSuccess(filmRepository.cachedOrFakeRepos)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetRepoCallback {
        fun onSuccess(repos: List<Film>)
        fun onError(error: String)
    }
}