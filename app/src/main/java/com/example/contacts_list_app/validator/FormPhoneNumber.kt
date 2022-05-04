package com.example.contacts_list_app.validator

import com.example.contacts_list_app.R
import com.example.contacts_list_app.di.StringsMyApp
import com.google.android.material.textfield.TextInputLayout


interface Validator{
    fun isValid() : Boolean
}

class FormPhoneNumber {

    fun add(phone : String) =
        phone.replace("([0-9]{2})([0-9]{4})([0-9]{4,5})".toRegex(),"($1) $2-$3")

    fun remove(phone: String) : String{
        return  phone
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")

    }
}

class ValidFormNumberWithDDD(private val textInputLayout: TextInputLayout): Validator{

    private val validDefault = ValidatorDefault(textInputLayout)
    private val editText get() = textInputLayout.editText
    private val telefone get() = editText?.text.toString()


    private fun validBetweenTenOrELevenDigits(telefone : String): Boolean {
        val digits = telefone.length
        if (digits < 10 || digits > 11) {
            textInputLayout.error = StringsMyApp.errorNumberDigitsPhone
            return false
        }
        return true
    }

    override
    fun isValid(): Boolean {
        if (!validDefault.isValid()) return false
        val unformattedPhone = FormPhoneNumber().remove(telefone)
        if (!validBetweenTenOrELevenDigits(unformattedPhone)) return false
        addFormater(unformattedPhone)
        return true
    }

    private fun addFormater(telefone : String) {
        val phoneFormatted = FormPhoneNumber().add(telefone)
        editText?.setText(phoneFormatted)
    }

}

class ValidatorDefault(val textInputLayout : TextInputLayout) : Validator{

    private fun validRequiredField(): Boolean {
        val editText = textInputLayout.editText
        val text = editText?.text.toString()
        if (text.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = StringsMyApp.requiredField
            return false
        }
        return true
    }

    override fun isValid():Boolean{
        if (!validRequiredField())return false
        cleanError()
        return true
    }

    private fun cleanError() {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

}