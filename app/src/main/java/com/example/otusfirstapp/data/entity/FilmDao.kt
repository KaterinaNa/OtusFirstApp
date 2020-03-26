package com.example.otusfirstapp.data.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class FilmDao : IFilmDao {

    @Transaction
    @Query("SELECT * FROM Film")
    abstract fun getFilmsWithLike(): List<FavoriteFilm>
}