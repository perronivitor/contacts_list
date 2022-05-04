package com.example.contacts_list_app.ui.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts_list_app.data.local.ContactsRepository
import com.example.contacts_list_app.data.local.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SaveContactViewModel(private val repository: ContactsRepository) : ViewModel() {

    var isBackContactFragment = MutableLiveData(false)

    var isShowMessageSuccess = MutableLiveData(false)

    private var _contact = MutableLiveData<Contact>()
    val contact get() = _contact

    private fun save(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.save(contact)
                isShowMessageSuccess.postValue(true)
                isBackContactFragment.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun delete(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.delete(contact)
                isShowMessageSuccess.postValue(true)
                isBackContactFragment.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun edit(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.edit(contact)
                isShowMessageSuccess.postValue(true)
                isBackContactFragment.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setContact(contact: Contact) {
        this._contact.postValue(contact)
    }

    fun actionButton(contact: Contact) {
        if (_contact.value != null) edit(contact) else save(contact)
    }

}