package com.example.contacts_list_app.ui.contacts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts_list_app.data.local.model.Contact

class ContactsAdapter(private val clickItem: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactsViewHolder>() {

    private var contacts: List<Contact>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        contacts?.get(position)?.let { contact ->
            holder.bind(contact)
            holder.itemView.setOnClickListener {
                clickItem(contact)
            }
        }


    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    fun addContacts(contacts: List<Contact>?) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}