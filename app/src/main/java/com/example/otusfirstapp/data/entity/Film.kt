package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Film (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("id")
    @ColumnInfo(name = "original_id")
    val originalId: Int,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val name: String,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val detail: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "like")
    val like : Boolean
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath', like='$like'}"
    }
}
