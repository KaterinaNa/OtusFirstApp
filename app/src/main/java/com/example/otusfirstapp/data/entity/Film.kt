package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Film (
    @SerializedName("id")
    @PrimaryKey
    val id: Int,

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
    var like: Boolean
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun like(state: Boolean? = null): Boolean {
        like = state ?: !like
        return like
    }

    override fun toString(): String {
        return "Film{id=$id, title='$name', overview=$detail', poster_path='$posterPath', like='$like'}"
    }
}
