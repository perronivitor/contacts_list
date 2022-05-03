package com.example.contacts_list_app.di

import com.example.contacts_list_app.data.local.ContactsRepository
import com.example.contacts_list_app.data.local.ContactsRepositoryImp
import com.example.contacts_list_app.ui.contacts.ContactsViewModel
import com.example.contacts_list_app.ui.save.SaveContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<ContactsRepository> { ContactsRepositoryImp()}

    viewModel { ContactsViewModel(get()) }

    viewModel { SaveContactViewModel(get())}

}