package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedFilm (
    @PrimaryKey(autoGenerate = true)
    val likeId: Int,

    @ColumnInfo(name = "film_id")
    val filmId: Int,

    @ColumnInfo(name = "like")
    var like: Boolean = true
) {

    fun like(state: Boolean? = null): Boolean {
        like = state ?: !like
        return like
    }

    override fun toString(): String {
        return "FavoriteFilm{id=$likeId, filmId='$filmId', like='$like'}"
    }
}