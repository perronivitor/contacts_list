package com.example.contacts_list_app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.contacts_list_app.data.local.ContactDao
import com.example.contacts_list_app.data.local.ContactRoomDatabase
import com.example.contacts_list_app.data.local.ContactsRepository
import com.example.contacts_list_app.data.local.ContactsRepositoryImp
import com.example.contacts_list_app.ui.contacts.ContactsViewModel
import com.example.contacts_list_app.ui.save.SaveContactViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel { ContactsViewModel(get()) }

    viewModel { SaveContactViewModel(get())}

}

val contactDataBase = module {

    fun contactDatabase(app: Application) = Room.databaseBuilder(
        app,
        ContactRoomDatabase::class.java,
        "contact_table"
    ).build()

    fun contactDao(dataBase : ContactRoomDatabase): ContactDao {
        return dataBase.contactDao()
    }

    single { contactDatabase(androidApplication()) }

    single { contactDao(get()) }

    single<ContactsRepository> { ContactsRepositoryImp(contactDao = get())}
}