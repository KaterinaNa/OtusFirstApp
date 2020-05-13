package com.example.otusfirstapp.data.entity

import com.google.gson.annotations.SerializedName

class FilmsResponse (
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: ArrayList<FilmRaw>,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)
