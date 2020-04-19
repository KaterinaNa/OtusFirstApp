package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fav (
    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val filmId: Int,

    @ColumnInfo(name = "fav")
    var fav: Boolean = true
) {
    override fun toString(): String {
        return "FavoriteFilm{filmId='$filmId', fav='$fav'}"
    }
}