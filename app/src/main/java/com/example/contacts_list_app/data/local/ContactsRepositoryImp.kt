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

    override suspend fun save(contact: Contact) {
        withContext(Dispatchers.IO) {
            contactDao.save(contact)
        }
    }

    override suspend fun delete(contact: Contact) {
        withContext(Dispatchers.IO){
            println("DELETANDO CONTATO PARA $contact")
            contactDao.delete(contact)
        }
    }

    override suspend fun edit(contact: Contact) {
        withContext(Dispatchers.IO){
            println("EDITANDO CONTATO PARA $contact")
            contactDao.edit(contact)
        }
    }

    override suspend fun getAllContacts(): List<Contact> {
        return withContext(Dispatchers.IO) {
            contactDao.getAllContacts()
        }
    }
}