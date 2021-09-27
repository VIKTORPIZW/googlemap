package com.example.clever_3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ContactsEntity::class], version = 7)
abstract class RoomContactsDb : RoomDatabase() {

    abstract fun contactDao(): ContactDAO?

    companion object {
        private var INSTANCE: RoomContactsDb? = null
        fun getAppDatabase(context: Context): RoomContactsDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, RoomContactsDb::class.java, "AppDb"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}