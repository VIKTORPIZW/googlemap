package com.example.clever_3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactsEntity::class], version = 1)
abstract class RoomAppDb : RoomDatabase() {
    abstract fun ContactsDAO(): ContactsDAO?

    companion object {
        private var INSTANCE: RoomAppDb? = null

        fun getAppDataBase(context: Context): RoomAppDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDB"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}