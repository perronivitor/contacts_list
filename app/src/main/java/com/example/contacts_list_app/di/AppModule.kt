package com.example.contacts_list_app.di

import com.example.contacts_list_app.ui.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { ContactsViewModel() }
}