package com.example.contacts_list_app.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contacts_list_app.data.local.model.Contact
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.contacts_list_app.databinding.FragmentSaveContactBinding
import com.example.contacts_list_app.validator.FormPhoneNumber
import com.example.contacts_list_app.validator.ValidFormNumberWithDDD
import com.example.contacts_list_app.validator.ValidacaoPadrao
import com.example.contacts_list_app.validator.Validator
import com.google.android.material.textfield.TextInputLayout

class SaveContactFragment : Fragment() {

    private var _binding: FragmentSaveContactBinding? = null
    private val binding: FragmentSaveContactBinding get() = _binding!!

    private val viewModel: SaveContactViewModel by viewModel()

    private var validators = mutableListOf<Validator>()

    private val args: SaveContactFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSaveContactBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()

        getArgs()

        viewModel.isBackContactFragment.observe(viewLifecycleOwner) { isBack ->
            if (isBack) {
                findNavController().navigateUp()
            }
        }

        viewModel.isSHowMessageSuccess.observe(viewLifecycleOwner) { isShow ->
            if (isShow) {
                messageSuccess()
            }
        }

        viewModel.contact.observe(viewLifecycleOwner){ contact ->
            contact?.let {
             binding.formFirstName.editText?.setText(it.firstName)
             binding.formSecondeName.editText?.setText(it.lastName)
             binding.formPhoneNumber.editText?.setText(it.phoneNumber)
            }
        }
    }

    private fun getArgs() {
        args.contact?.let { contact ->
            viewModel.setContact(contact)
        }
    }

    private fun messageSuccess() {
        Toast.makeText(
            requireContext(),
            "Cadastro realizado com sucesso",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun listeners() {
        with(binding) {

            confButtonSaveContact(formButtonSave)

            addValidatorPadrao(formFirstName)

            addValidatorPadrao(formSecondeName)

            confFieldPhoneNumber(formPhoneNumber)

        }
    }

    private fun confButtonSaveContact(formButtonSave: Button) {
        formButtonSave.setOnClickListener {
            if (formIsValid()) {
                viewModel.save(
                    with(binding) {
                        Contact(
                            firstName = formFirstName.editText?.text.toString(),
                            lastName = formSecondeName.editText?.text.toString(),
                            phoneNumber = formPhoneNumber.editText?.text.toString()
                        )
                    }
                )
            }
        }
    }

    private fun addValidatorPadrao(textInputLayout: TextInputLayout) {
        val validator = ValidacaoPadrao(textInputLayout)
        validators.add(validator)
        textInputLayout.editText?.setOnFocusChangeListener { view, hasFocus ->
            if (validator.isValid()) return@setOnFocusChangeListener
        }
    }

    private fun confFieldPhoneNumber(textFieldPhone: TextInputLayout) {
        val editTextPhone = textFieldPhone.editText
        val validatorPhone = ValidFormNumberWithDDD(textFieldPhone)
        val formata = FormPhoneNumber()
        validators.add(ValidFormNumberWithDDD(textFieldPhone))
        editTextPhone?.setOnFocusChangeListener { view, hasFocus ->
            val telefone = editTextPhone.text.toString()
            if (hasFocus) {
                editTextPhone.setText(formata.remove(telefone))
            } else {
                validatorPhone.isValid()
            }
        }
    }

    private fun formIsValid(): Boolean {
        var isValid = false
        validators.forEach { validator ->
            isValid = validator.isValid()
        }
        return isValid
    }

}