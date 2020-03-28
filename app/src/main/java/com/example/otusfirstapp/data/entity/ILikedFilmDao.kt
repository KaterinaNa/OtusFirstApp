package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface ILikedFilmDao {

    @Insert
    fun insert(LikedFilm: LikedFilm?): Long

    @Insert
    fun insertLikedFilms(vararg LikedFilm: LikedFilm?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLikedFilms(LikedFilms: ArrayList<LikedFilm?>?): List<Long?>?

    @Update
    fun update(LikedFilm: LikedFilm?)

    @Update
    fun updateLikedFilms(Films: ArrayList<LikedFilm?>?): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLikedFilms(vararg LikedFilms: LikedFilm?)

    @Delete
    fun delete(LikedFilm: LikedFilm?)

    @Delete
    fun deleteLikedFilms(Films: ArrayList<LikedFilm?>?): Int

    @Query("SELECT * FROM LikedFilm")
    fun getAll(): List<LikedFilm?>?

    @Query("SELECT * FROM LikedFilm WHERE id = :id")
    fun getById(id: Long): LikedFilm?


}