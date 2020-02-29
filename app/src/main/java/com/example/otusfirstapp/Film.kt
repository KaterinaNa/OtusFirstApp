package com.example.otusfirstapp

import com.google.gson.annotations.SerializedName

data class Film (
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val name: String,

    @SerializedName("overview")
    val detail: String,

    @SerializedName("poster_path")
    val posterPath: String,

    var like: Boolean
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }
}
