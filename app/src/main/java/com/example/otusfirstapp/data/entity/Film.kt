package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Film (
    val id: Int = 0,

    @ColumnInfo(name = "title")
    var name: String = "",

    @ColumnInfo(name = "overview")
    var detail: String = "",

    @ColumnInfo(name = "poster_path", defaultValue = "")
    var posterPath: String = "",

    @ColumnInfo(name = "sort_order")
    var sortOrder: Int = 0,

    @ColumnInfo(name = "fav")
    var fav : Boolean = false,

    @ColumnInfo(name = "later")
    var later : Long = 0
    )
    {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun fav(state: Boolean? = null): Boolean {
        fav = state ?: !fav
        return fav
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath', like='$fav', later='$later'}"
    }
}
