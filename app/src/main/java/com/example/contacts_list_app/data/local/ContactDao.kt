package com.example.contacts_list_app.data.local

import android.content.Context
import androidx.room.*
import com.example.contacts_list_app.data.local.model.Contact


@Dao
interface ContactDao{

    @Query("SELECT * FROM contact_table")
    suspend fun getAllContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Update(entity = Contact::class)
    suspend fun edit(contact: Contact)

}

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase () {
    abstract  fun contactDao() : ContactDao
}