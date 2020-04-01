package com.example.otusfirstapp.data

import android.content.Context
import androidx.room.Room

object Db {

    private var instance: AppDb? = null

    fun getInstance(context: Context? = null): AppDb? {
        if (instance == null) {
            synchronized(AppDb::class) {
                instance = Room.databaseBuilder(
                    context!!,
                    AppDb::class.java,
                    "db-name.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_1_2)
                    .addCallback(
                        DbCallback(context)
                    )
                    .build()
            }
        }
        return instance
}

    fun destroyInstance() {
        instance?.close()
        instance = null
    }
}