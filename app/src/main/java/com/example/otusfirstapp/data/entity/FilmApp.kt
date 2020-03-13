package com.example.otusfirstapp.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase

const val TAG = "AppDb"

@Database(entities = arrayOf(Film::class), version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao

}