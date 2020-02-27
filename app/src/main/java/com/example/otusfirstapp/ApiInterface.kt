package com.example.otusfirstapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<FilmsResponse>

    @GET("movie/{id}")
    fun getFilmInfo(@Path("id") id: Int, @Query("api-key") apiKey: String): Call<Film>


}