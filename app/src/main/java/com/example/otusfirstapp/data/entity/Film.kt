package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Film (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Transient
    val id: Int = 0,

    @ColumnInfo(name = "original_id")
    @SerializedName("id")
    var originalId: Int = 0,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var name: String = "",

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var detail: String = "",

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String = "",

    @ColumnInfo(name = "like")
    @Transient
    var like : Boolean = false
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun like(state: Boolean? = null): Boolean {
        like = state ?: !like
        return like
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath', like='$like'}"
    }
}
