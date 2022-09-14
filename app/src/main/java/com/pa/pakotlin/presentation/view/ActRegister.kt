package com.pa.pakotlin.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mh.custom_alert.CustomAlert
import com.mh.custom_alert.Theme
import com.mh.custom_alert.Type
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.ActResgisterBinding
import com.pa.pakotlin.model.Gender
import com.pa.pakotlin.presentation.model.RegisterModel
import com.pa.pakotlin.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActRegister : AppCompatActivity() {
    private lateinit var customAlert: CustomAlert
    private lateinit var binding: ActResgisterBinding
    private val viewModelRegister: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActResgisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startDropDownLists()
        deleteError()
        viewModelRegister.date.observe(this) {
            binding.etBirthday.setText(it)
        }
        binding.etBirthday.setOnClickListener {
            binding.inputBirthday.error = null
            viewModelRegister.datePiker(this)
        }
        binding.btnRegister.setOnClickListener {
            val register = RegisterModel()
            if (checkValidInput(register)) {
                println("Datos:$register")
                viewModelRegister.registerUser(register, this)
            }
        }

        viewModelRegister.stateRegister.observe(this) {
            when (it) {
                is LoginState.Loading -> {
                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setTitle("Registro")
                    customAlert.setType(Type.PROGRESS)
                    customAlert.setMessage("Cargando")
                    customAlert.show()
                    println("Api:Cargando")
                }
                is LoginState.Successful -> {
                    showAlert(Type.SUCCESS, "Exitoso", true)
                    println("Api:Exitoso")
                }
                is LoginState.Error -> {
                    // Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }
                    showAlert(Type.FAIL, it.message, false)
                    binding.inputUser.error = it.message
                    println("Api:Error${it.message}")
                }
            }
        }
    }

    private fun deleteError() {
        binding.etName.deleteError(binding.inputName)
        binding.etLastName.deleteError(binding.inputLastName)
        binding.etSecondLastName.deleteError(binding.inputSecondLastName)
        binding.etGender.deleteError(binding.inputGender)
        binding.etEmail.deleteError(binding.inputEmail)
        binding.etState.deleteError(binding.inputState)
        binding.etPhone.deleteError(binding.inputPhone)
        binding.etUser.deleteError(binding.inputUser)
        binding.etPassword.deleteError(binding.inputPassword)
        binding.etPasswordConfirm.deleteError(binding.inputPasswordConfirm)
    }

    private fun EditText.deleteError(input: TextInputLayout) {
        setOnClickListener {
            input.error = null
        }
        setOnFocusChangeListener { _, _ ->
            input.error = null
            error = null
        }
    }

    private fun EditText.isEmptyAndBlank() =
        text.toString().isEmpty() && text.toString().isBlank()

    private fun startDropDownLists() {
        binding.etState.setAdapter(
            ArrayAdapter(
                this,
                R.layout.item_pablo_option,
                resources.getStringArray(R.array.pa_states)
            )
        )
        binding.etGender.setAdapter(ArrayAdapter(this, R.layout.item_pablo_option, getListGender()))
    }

    private fun getListGender() = listOf(Gender.MALE.gender, Gender.FAME.gender)

    private fun checkValidInput(register: RegisterModel): Boolean {
        var flag = true
        if (binding.etName.isEmptyAndBlank()) {
            binding.inputName.error = getString(R.string.pa_register_error_name)
            flag = false
        } else register.name = binding.etName.text.toString()
        if (binding.etLastName.isEmptyAndBlank()) {
            binding.inputLastName.error = getString(R.string.pa_register_error_last_name)
            flag = false
        } else register.lastName = binding.etLastName.text.toString()

        if (binding.etSecondLastName.isEmptyAndBlank()) {
            binding.inputSecondLastName.error =
                getString(R.string.pa_register_error_second_last_name)
            flag = false
        } else register.secondLastName = binding.etSecondLastName.text.toString()
        if (binding.etBirthday.text.toString() == "dd/mm/yyyy") {
            binding.inputBirthday.error = getString(R.string.pa_register_error_birthday)
            flag = false
        } else register.birthday = binding.etBirthday.text.toString()
        if (binding.etGender.isEmptyAndBlank()) {
            binding.inputGender.error = getString(R.string.pa_register_error_gender)
            flag = false
        } else register.gender = binding.etGender.text.toString()
        if (binding.etEmail.isEmptyAndBlank()) {
            binding.inputEmail.error = getString(R.string.pa_register_error_email)
            flag = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
            binding.inputEmail.error = getString(R.string.pa_register_error_email_format)
            flag = false
        } else register.email = binding.etEmail.text.toString()
        if (binding.etState.isEmptyAndBlank()) {
            binding.inputState.error = getString(R.string.pa_register_error_state)
            flag = false
        } else register.state = binding.etState.text.toString()
        if (binding.etPhone.isEmptyAndBlank()) {
            binding.inputPhone.error = getString(R.string.pa_register_error_phone)
            flag = false
        } else register.phone = binding.etPhone.text.toString()
        if (binding.etUser.isEmptyAndBlank()) {
            binding.inputUser.error = getString(R.string.pa_register_error_user)
            flag = false
        } else register.user = binding.etUser.text.toString()
        if (binding.etPassword.isEmptyAndBlank()) {
            binding.inputPassword.error = getString(R.string.pa_register_error_password)
            binding.inputPasswordConfirm.error =
                getString(R.string.pa_register_error_password_confirm)
            flag = false
        } else if (binding.etPassword.text.toString() != binding.etPasswordConfirm.text.toString()) {
            binding.inputPasswordConfirm.error =
                getString(R.string.pa_register_error_password_confirm)
            flag = false
        } else register.password = binding.etPassword.text.toString()

        return flag
    }

    private fun showAlert(type: IntArray, message: String, boolean: Boolean) {
        customAlert = CustomAlert(this, Theme.SYSTEM)
        customAlert.setTitle("Registro")
        customAlert.setType(type)
        customAlert.setMessage(message)
        customAlert.setPositiveText(
            "Ok"
        ) {
            if (boolean) {
                val actDashboard = Intent(this, MainActivity::class.java)
                startActivity(actDashboard)
                finish()
            }
            customAlert.dismiss()
        }
        customAlert.show()
    }
}
