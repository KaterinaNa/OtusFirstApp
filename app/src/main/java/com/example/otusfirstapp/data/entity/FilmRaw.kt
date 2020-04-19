package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FilmRaw (
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

    @ColumnInfo(name = "sort_order")
    @Transient
    var sortOrder: Int = 0
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath'}"
    }
}
