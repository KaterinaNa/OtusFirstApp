package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface IFilmDao {

    //Examples of insertion methods
    @Insert
    fun insert(Film: Film?)

    @Insert
    fun insertSingleFilm(Film: Film?): Long

    @Insert
    fun insertBothFilm(Film1: Film?, Film2: Film?)

    @Insert
    fun insertFilms(vararg Films: Film?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(Films: List<Film?>?): List<Long?>?

    @Insert
    fun insertPublisherAndFilms(
        Publisher: Film?,
        Publishers: List<Film?>?
    )


    //Examples of update methods
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(vararg Films: Film?)

    @Update
    fun update(Film: Film?)

    @Update
    fun updateFilms(Films: List<Film?>?): Int


    //Examples of deletion methods

    @Delete
    fun deleteFilm(Film: Film?)

    @Delete
    fun deletePublishers(Films: List<Film?>?): Int




    @Query("SELECT * FROM Film")
    fun getAll(): List<Film?>?

    @Query("SELECT * FROM Film WHERE id = :id")
    fun getById(id: Long): Film?


}