package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface IFavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fav: Fav?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(favs: ArrayList<Fav>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(fav: Fav?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(favs: ArrayList<Fav>?): Int

    @Delete
    fun deleteFilm(fav: Fav?)

    @Delete
    fun deleteFilms(favs: ArrayList<Fav>?): Int

    @Query("SELECT * FROM Fav")
    fun getAll(): List<Fav>?
}