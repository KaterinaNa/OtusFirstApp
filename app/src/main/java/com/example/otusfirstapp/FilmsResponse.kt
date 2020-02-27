package com.example.otusfirstapp

import com.google.gson.annotations.SerializedName

class FilmsResponse {
    @SerializedName("page")
    val page = 0
    @SerializedName("results")
    val results: ArrayList<Film>? = null
    @SerializedName("total_results")
    val totalResults = 0
    @SerializedName("total_pages")
    val totalPages = 0
}