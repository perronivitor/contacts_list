package com.example.contacts_list_app.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class Contact(

    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "first_name", defaultValue = "")
    val firstName: String,

    @ColumnInfo(name = "last_name", defaultValue = "")
    val lastName: String,

    @ColumnInfo(name = "phone_number", defaultValue = "")
    val phoneNumber: String,

) : Parcelable