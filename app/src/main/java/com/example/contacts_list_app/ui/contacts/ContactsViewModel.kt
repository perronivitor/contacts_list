package com.example.contacts_list_app.ui.contacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts_list_app.data.local.ContactsRepository
import com.example.contacts_list_app.data.local.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    var contactsList = MutableLiveData<List<Contact>?>()

    var viewPagerChild = MutableLiveData<Int>()

    init {
        getContacts()
    }

    private fun getContacts() = viewModelScope.launch(Dispatchers.IO) {
        viewPagerChild.postValue(0)
        try {

            val repo = repository.getAllContacts()

            viewPagerChild.postValue(if (repo.isNullOrEmpty()) 2 else {
                contactsList.postValue(repo)
                1
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}