package com.example.contacts_list_app.data.local

import com.example.contacts_list_app.data.local.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface ContactsRepository {

    suspend fun save(contact: Contact)

    suspend fun delete(contact: Contact)

    suspend fun edit(contact: Contact)

    suspend fun getAllContacts(): List<Contact>
}

class ContactsRepositoryImp(private val contactDao: ContactDao) : ContactsRepository {

    var mockList = mutableListOf(
        Contact(
            firstName = "Vitor",
            lastName = "Perroni",
            phoneNumber = "47 996699001",
        ),
    )

    override suspend fun save(contact: Contact) {
        withContext(Dispatchers.IO) {
            mockList.add(contact)
        }
    }

    override suspend fun delete(contact: Contact) {
    }

    override suspend fun edit(contact: Contact) {
    }

    override suspend fun getAllContacts(): List<Contact> {
        return withContext(Dispatchers.IO) {
            mockList
        }
    }
}