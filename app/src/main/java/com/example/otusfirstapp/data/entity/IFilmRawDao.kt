package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface IFilmRawDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: FilmRaw?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: ArrayList<FilmRaw>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(film: FilmRaw?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(films: ArrayList<FilmRaw>?): Int

    @Delete
    fun deleteFilm(film: FilmRaw?)

    @Delete
    fun deleteFilms(films: ArrayList<FilmRaw>?): Int

    @Query("SELECT FilmRaw.*, Fav.fav FROM FilmRaw LEFT JOIN Fav ON (FilmRaw.id = Fav.film_id) ORDER BY FilmRaw.sort_order")
    fun getAll(): List<Film?>?
}