package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface ILaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(later: Later?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(laters: ArrayList<Later>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(later: Later?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(laters: ArrayList<Later>?): Int

    @Delete
    fun deleteFilm(laters: Later?)

    @Delete
    fun deleteFilms(laters: ArrayList<Later>?): Int

    @Query("SELECT * FROM Later")
    fun getAll(): List<Later>?
}