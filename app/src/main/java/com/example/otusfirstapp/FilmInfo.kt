package com.example.otusfirstapp


import com.google.gson.annotations.SerializedName

class FilmInfo {
    @SerializedName("id")
    val posterId = "id"
    @SerializedName("title")
    val name = "title"
    @SerializedName("overview")
    val detail = "overview"
    @SerializedName("poster_path")
    val posterPath = "posterpath"
}

fun getPosterPath(): String {
    return "https://image.tmdb.org/t/p/w500$ posterPath"
}







