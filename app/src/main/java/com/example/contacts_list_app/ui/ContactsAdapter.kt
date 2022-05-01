package com.example.contacts_list_app.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_list_app.Contact

class ContactsAdapter (private val contacts : List<Contact>?) : RecyclerView.Adapter<ContactsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        contacts?.get(position)?.let { contact ->
            holder.bind(contact)
        }
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    fun refresh(){
        notifyDataSetChanged()
    }
}