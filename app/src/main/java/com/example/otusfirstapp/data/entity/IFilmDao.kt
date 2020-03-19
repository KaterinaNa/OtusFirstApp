package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface IFilmDao {

    @Insert
    fun insertSingleFilm(Film: Film?): Long

    @Insert
    fun insertFilms(vararg Films: Film?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(Films: ArrayList<Film?>?): List<Long?>?


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(vararg Films: Film?)

    @Update
    fun update(Film: Film?)

    @Update
    fun updateFilms(Films: ArrayList<Film?>?): Int

    @Delete
    fun deleteFilm(Film: Film?)

    @Delete
    fun deleteFilms(Films: ArrayList<Film?>?): Int

    @Query("SELECT * FROM Film")
    fun getAll(): List<Film?>?

    @Query("SELECT * FROM Film WHERE id = :id")
    fun getById(id: Long): Film?


}