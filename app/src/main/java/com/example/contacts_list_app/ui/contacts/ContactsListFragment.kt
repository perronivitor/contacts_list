package com.example.contacts_list_app.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts_list_app.R
import com.example.contacts_list_app.data.local.model.Contact
import com.example.contacts_list_app.databinding.FragmentContactsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsListFragment : Fragment() {

    private var _binding: FragmentContactsListBinding? = null
    val binding: FragmentContactsListBinding get() = _binding!!

    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var contactAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentContactsListBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContactsListAdapter()
        observerInitialLoadState()

        listeners()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getContacts()
    }

    private fun listeners() {
        with(binding) {
            addContactButton.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_contactsListFragment_to_saveContactFragment)
            }
        }
    }

    private fun observerInitialLoadState() {

        viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->
            contactAdapter.addContacts(contacts)
        }

        viewModel.viewPagerChild.observe(viewLifecycleOwner) { child ->
            binding.contactViewFlipper.displayedChild = child
        }

    }

    private fun initContactsListAdapter() {
        with(binding.recyclerContacts) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            contactAdapter = ContactsAdapter { contact ->
                navToEditOrDeleteContact(contact)
            }

            adapter = contactAdapter
        }
    }

    private fun navToEditOrDeleteContact(contact: Contact) {
        findNavController().navigate(
            directions = ContactsListFragmentDirections
                .actionContactsListFragmentToSaveContactFragment()
                .setContact(contact)
        )
    }

    companion object {
        const val FLIPPER_CHILD_LOADING = 0
        const val FLIPPER_CHILD_CONTACTS = 1
        const val FLIPPER_CHILD_ERROR = 2
    }

}