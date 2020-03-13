package com.example.otusfirstapp.data.entity

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class DbCallback(private val ctx: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.d(TAG, "DbCallback onCreate")
        Executors.newSingleThreadScheduledExecutor().execute(Runnable {
            val film = ListData.films[0]
            Db.getInstance(ctx)?.getFilmDao()?.insert(film)
            Log.d(TAG, "DbCallback executed")

        })
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        // do something every time database is open
    }

}
