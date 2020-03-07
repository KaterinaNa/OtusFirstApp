package com.example.otusfirstapp.data

import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String,

        @Query("page")
        page: Int = 1
    ): Call<FilmsResponse>

    @GET("movie/{id}")
    fun getFilmInfo(
        @Path("id")
        id: Int,

        @Query("api-key")
        apiKey: String
    ): Call<Film>
}
