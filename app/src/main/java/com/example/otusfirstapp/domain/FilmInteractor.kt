package com.example.otusfirstapp.domain

import com.example.otusfirstapp.data.FilmRepository
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.FilmsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmInteractor(private val filmService: FilmService, private val filmRepository: FilmRepository) {

    fun getFilms(apiKey: String, page: Int, callback: GetFilmCallback) {

        filmService.getTopRatedMovies(apiKey, page).enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                if (response.isSuccessful) {
                    val films = response.body()?.results
                    if (films == null) {
                        callback.onError("API returned null results")
                        return
                    }

                    filmRepository.addToCache(films)

                    callback.onSuccess(filmRepository.cachedOrFakeFilms)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetFilmCallback {
        fun onSuccess(films: ArrayList<Film>)
        fun onError(error: String)
    }
}