package com.example.contacts_list_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.contacts_list_app.Contact
import com.example.contacts_list_app.databinding.FragmentContactsListBinding

class ContactsListFragment : Fragment() {

    private var _binding: FragmentContactsListBinding? = null
    val binding: FragmentContactsListBinding get() = _binding!!

    private val viewModel: ContactsViewModel by viewModels()

    private lateinit var contactAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentContactsListBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerInitialLoadState()

    }

    private fun observerInitialLoadState() {

        viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->

        }

    }

    private fun initContactsListAdapter(list: List<Contact>) {
        with(binding.recyclerContacts) {
            setHasFixedSize(true)
            adapter = ContactsAdapter(list)
        }
    }


    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CONTACTS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }

}