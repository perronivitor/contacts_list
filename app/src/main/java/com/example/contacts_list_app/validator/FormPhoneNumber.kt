package com.example.contacts_list_app.validator

import com.google.android.material.textfield.TextInputLayout


interface Validator{
    fun isValid() : Boolean
}

class FormPhoneNumber {

    fun add(telefone : String) =
        telefone.replace("([0-9]{2})([0-9]{4})([0-9]{4,5})".toRegex(),"($1) $2-$3")

    fun remove(telefone: String) : String{
        return  telefone
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")

    }
}

class ValidFormNumberWithDDD(private val textInputLayout: TextInputLayout): Validator{

    private val DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 e 11 digitos"
    private val validDefault = ValidacaoPadrao(textInputLayout)
    private val editText get() = textInputLayout.editText
    private val telefone get() = editText?.text.toString()


    private fun validaEntreDezOuOnzeDigitos(telefone : String): Boolean {
        val digitos = telefone.length
        if (digitos < 10 || digitos > 11) {
            textInputLayout.error = DEVE_TER_DEZ_OU_ONZE_DIGITOS
            return false
        }
        return true
    }

    override
    fun isValid(): Boolean {
        if (!validDefault.isValid()) return false
        val telefoneSemFormato = FormPhoneNumber().remove(telefone)
        if (!validaEntreDezOuOnzeDigitos(telefoneSemFormato)) return false
        adicionaFormatacao(telefoneSemFormato)
        return true
    }

    private fun adicionaFormatacao(telefone : String) {
        val telefoneFormatado = FormPhoneNumber().add(telefone)
        editText?.setText(telefoneFormatado)
    }

}

class ValidacaoPadrao(val textInputLayout : TextInputLayout) : Validator{

    private val CAMPO_OBRIGATORIO = "Campo Obrigatório"

    private fun validaCampoObrigatorio(): Boolean {
        val editText = textInputLayout.editText
        val text = editText?.text.toString()
        if (text.isEmpty()) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = CAMPO_OBRIGATORIO
            return false
        }
        return true
    }

    override fun isValid():Boolean{
        if (!validaCampoObrigatorio())return false
        cleanError()
        return true
    }

    private fun cleanError() {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

}