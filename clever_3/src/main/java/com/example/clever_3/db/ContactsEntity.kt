package com.example.clever_3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "contacts_list")
data class ContactsEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "number") val number : String,
    @ColumnInfo(name = "email") val email : String
)


