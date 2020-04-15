package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface IFilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: Film?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(films: ArrayList<Film>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(film: Film?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(films: ArrayList<Film>?): Int

    @Delete
    fun deleteFilm(film: Film?)

    @Delete
    fun deleteFilms(films: ArrayList<Film>?): Int

    @Query("SELECT * FROM Film")
    fun getAll(): List<Film?>?

    @Query("SELECT * FROM Film WHERE id = :id")
    fun getById(id: Long): Film?


}