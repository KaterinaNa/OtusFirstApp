package com.example.otusfirstapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.otusfirstapp.data.entity.*

@Database(entities = [Film::class, LikedFilm::class], version = 3)
abstract class AppDb : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
    abstract fun getLikedFilmDao(): LikedFilmDao
}