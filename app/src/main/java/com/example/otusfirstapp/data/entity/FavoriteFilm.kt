package com.example.otusfirstapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteFilm (

    @Embedded
    val film: Film,

    @Relation(parentColumn = "id", entityColumn = "film_id")
    val likedFilm: LikedFilm

)




