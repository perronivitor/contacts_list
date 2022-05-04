package com.example.contacts_list_app.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts_list_app.data.local.ContactsRepository
import com.example.contacts_list_app.data.local.model.Contact
import com.example.contacts_list_app.ui.contacts.ContactsListFragment.Companion.FLIPPER_CHILD_CONTACTS
import com.example.contacts_list_app.ui.contacts.ContactsListFragment.Companion.FLIPPER_CHILD_ERROR
import com.example.contacts_list_app.ui.contacts.ContactsListFragment.Companion.FLIPPER_CHILD_LOADING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    var contactsList = MutableLiveData<List<Contact>?>()

    var viewPagerChild = MutableLiveData<Int>()

    init {
        getContacts()
    }

    fun getContacts() = viewModelScope.launch(Dispatchers.IO) {
        viewPagerChild.postValue(FLIPPER_CHILD_LOADING)
        try {

            val repo = repository.getAllContacts()

            viewPagerChild.postValue(if (repo.isNullOrEmpty()) FLIPPER_CHILD_ERROR else {
                contactsList.postValue(repo)
                FLIPPER_CHILD_CONTACTS
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}