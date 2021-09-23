package com.example.clever_3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contactsInfo")
data class ContactsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "number") val number: String?,
    @ColumnInfo(name = "email") val email: String?
)

