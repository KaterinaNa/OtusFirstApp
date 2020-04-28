package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class FilmLater {
    @Entity
    data class FilmLater (
        @PrimaryKey
        @SerializedName("id")
        val id: Int = 0,

        @ColumnInfo(name = "title")
        @SerializedName("title")
        var name: String = "",

        @ColumnInfo(name = "overview", defaultValue = "")
        @SerializedName("overview")
        var detail: String = "",

        @ColumnInfo(name = "poster_path", defaultValue = "")
        @SerializedName("poster_path")
        var posterPath: String = "",

        @ColumnInfo(name = "time_later")
        @Transient
        var time_later: Int = 0
    ) {
        fun poster(): String {
            return "https://image.tmdb.org/t/p/w500$posterPath"
        }

        override fun toString(): String {
            return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath'}"
        }
    }
}