package com.example.otusfirstapp.data.entity

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Dao
import androidx.room.Transaction
import java.nio.file.Files.delete

@Dao
abstract class FilmDao : IFilmDao {

    @Transaction
    open fun insertAndDeleteInTransaction(
        newFilm: Film?,
        oldFilm: Film?
    ) { // Anything inside this method runs in a single transaction.
        insert(newFilm)
        delete(oldFilm)
    }

}