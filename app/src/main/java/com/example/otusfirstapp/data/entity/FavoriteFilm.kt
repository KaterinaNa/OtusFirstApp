package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

class FavoriteFilm (

    @Embedded
    val film: Film,

    @ColumnInfo(name = "like")
    val like : Boolean,

    @Relation(parentColumn = "id", entityColumn = "film_id")
    val likedFilm: LikedFilm

)




