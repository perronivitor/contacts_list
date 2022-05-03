package com.example.contacts_list_app.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts_list_app.R
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
            contactAdapter = ContactsAdapter()
            adapter = contactAdapter
        }
    }


    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CONTACTS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }

}