package com.example.clever_3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ContactsDAO {
    @Query("SELECT * FROM contactsInfo ORDER BY id DESC")
    fun getAllContactInfo (): List<ContactsEntity>?

    @Insert
    fun insertContact(contact: ContactsEntity?)

    @Insert
    fun deleteContact(contact: ContactsEntity?)

    @Insert
    fun updateContact(contact: ContactsEntity?)
}