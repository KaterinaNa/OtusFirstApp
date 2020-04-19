package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Film (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Transient
    val id: Int = 0,

    @ColumnInfo(name = "original_id")
    var originalId: Int = 0,

    @ColumnInfo(name = "title")
    var name: String = "",

    @ColumnInfo(name = "overview")
    var detail: String = "",

    @ColumnInfo(name = "poster_path", defaultValue = "")
    var posterPath: String = "",

    @ColumnInfo(name = "fav")
    @Transient
    var fav : Boolean = false
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun fav(state: Boolean? = null): Boolean {
        fav = state ?: !fav
        return fav
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath', like='$fav'}"
    }
}
