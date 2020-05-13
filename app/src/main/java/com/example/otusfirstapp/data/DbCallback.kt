package com.example.otusfirstapp.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

class DbCallback(private val ctx: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.i(TAG, "DbCallback onCreate")
        Executors.newSingleThreadScheduledExecutor().execute {
            Db.getInstance(ctx)?.getFilmRawDao()?.getAll()
            Log.i(TAG, "DbCallback executed")
        }
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        // do something every time database is open
    }

    companion object {
        const val TAG = "DbCallback"
    }

}
