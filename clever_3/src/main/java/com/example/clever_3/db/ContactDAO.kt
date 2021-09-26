package com.example.clever_3.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ContactDAO {



    @Query("SELECT * FROM contacts_list")
    fun getAllContactsInfo(): List<ContactsEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactsEntity)
}