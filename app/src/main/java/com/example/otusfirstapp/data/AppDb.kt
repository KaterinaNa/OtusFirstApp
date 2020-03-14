package com.example.otusfirstapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.FilmDAO

const val TAG = "AppDb"

@Database(entities = arrayOf(Film::class), version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun getFilmDAO(): FilmDAO

}