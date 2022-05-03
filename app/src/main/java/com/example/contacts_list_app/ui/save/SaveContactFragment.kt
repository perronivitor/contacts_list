package com.example.contacts_list_app.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contacts_list_app.Contact
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.contacts_list_app.databinding.FragmentSaveContactBinding

class SaveContactFragment : Fragment() {

    private var _binding: FragmentSaveContactBinding? = null
    private val binding: FragmentSaveContactBinding get() = _binding!!

    private val viewModel : SaveContactViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSaveContactBinding.inflate(inflater,container,false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()

        viewModel.isBackContactFragment.observe(viewLifecycleOwner){isBack ->
            if(isBack){
                findNavController().navigateUp()
            }
        }
    }

    private fun listeners() {
        with(binding){
            formButtonSave.setOnClickListener {
                viewModel.save(Contact(
                    "teste",
                    "save",
                    "47 999999999"
                ))
            }
        }
    }


}