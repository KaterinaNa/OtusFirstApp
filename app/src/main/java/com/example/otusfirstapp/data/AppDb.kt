package com.example.otusfirstapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otusfirstapp.data.entity.*

@Database(entities = [FilmRaw::class, Fav::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun getFilmRawDao(): FilmRawDao
    abstract fun getFavDao(): FavDao
}