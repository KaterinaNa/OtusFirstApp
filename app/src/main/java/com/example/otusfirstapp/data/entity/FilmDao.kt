package com.example.otusfirstapp.data.entity

import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class FilmDao : IFilmDao {

    @Query("SELECT * FROM Film")
    abstract fun getFilmsWithLike(): List<FavoriteFilm>
}