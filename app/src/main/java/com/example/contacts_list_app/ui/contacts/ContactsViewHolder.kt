package com.example.contacts_list_app.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_list_app.Contact
import com.example.contacts_list_app.databinding.ItemContactBinding

class ContactsViewHolder(itemContactBinding : ItemContactBinding) : RecyclerView.ViewHolder(itemContactBinding.root) {

    private val fullName = itemContactBinding.fullName

    fun bind(contact : Contact){
        fullName.text = "${contact.firstName} ${contact.lastName}"
    }

    companion object{
        fun create(parent: ViewGroup) : ContactsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBInding = ItemContactBinding.inflate(inflater,parent,false)
            return ContactsViewHolder(itemBInding)
        }
    }
}