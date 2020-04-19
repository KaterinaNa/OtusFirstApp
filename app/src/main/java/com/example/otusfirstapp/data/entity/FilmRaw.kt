package com.example.otusfirstapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FilmRaw (
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

    @ColumnInfo(name = "poster_path", defaultValue = "")
    @SerializedName("poster_path")
    var posterPath: String = ""
) {
    fun poster(): String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    override fun toString(): String {
        return "Film{id=, title='$name', overview=$detail', poster_path='$posterPath'}"
    }
}
