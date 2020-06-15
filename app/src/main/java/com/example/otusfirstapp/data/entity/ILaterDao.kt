package com.example.otusfirstapp.data.entity

import androidx.room.*

@Dao
interface ILaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(later: Later?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaters(laters: ArrayList<Later>?): List<Long>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(later: Later?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateLaters(laters: ArrayList<Later>?): Int

    @Delete
    fun deleteLater(laters: Later?)

    @Delete
    fun deleteLaters(laters: ArrayList<Later>?): Int

    @Query("SELECT * FROM Later")
    fun getAll(): List<Later>?
}