package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Later (
    @PrimaryKey
    @ColumnInfo(name = "film_id")
    val filmId: Int,

    @ColumnInfo(name = "show_time")
    var showTime: Long = 0
) {
    override fun toString(): String {
        return "Later{filmId='$filmId', later='$showTime'}"
    }
}