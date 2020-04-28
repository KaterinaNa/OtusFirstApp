package com.example.otusfirstapp.data.entity

import androidx.room.*


@Dao
interface IFilmLaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: FilmLater?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: ArrayList<FilmLater>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(film: FilmLater?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(films: ArrayList<FilmLater>?): Int

    @Delete
    fun deleteFilm(film: FilmLater?)

    @Delete
    fun deleteFilms(films: ArrayList<FilmLater>?): Int

    @Query("SELECT FilmLater.*, Later.later FROM FilmRaw LEFT JOIN Fav ON (FilmLater.id = Later.film_id) ORDER BY FilmLater.time_later")
    fun getAll(): List<Film?>?

    @Query("DELETE FROM FilmLater")
    fun deleteAll(): Int
}