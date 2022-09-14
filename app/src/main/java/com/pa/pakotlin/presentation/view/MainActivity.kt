package com.pa.pakotlin.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mh.custom_alert.CustomAlert
import com.mh.custom_alert.Theme
import com.mh.custom_alert.Type
import com.pa.pakotlin.LoginState
import com.pa.pakotlin.data.model.LoginRequest
import com.pa.pakotlin.databinding.ActivityMainBinding
import com.pa.pakotlin.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var customAlert: CustomAlert
    private lateinit var bilder: AlertDialog.Builder
    private lateinit var binding: ActivityMainBinding
    private val viewModelMain by viewModel<MainViewModel>()

    // private val viewModelMain: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customAlert = CustomAlert(this, Theme.SYSTEM)
        customAlert.setTitle("Login")
        bilder = AlertDialog.Builder(this)
        bilder.setTitle("Login")
        binding.btnLogin.setOnClickListener {
            if (checkValidInput()) {
                deleteError()
                viewModelMain.login(
                    LoginRequest(
                        binding.etUser.text.toString(),
                        binding.etPassword.text.toString()
                    ),
                    this
                )
            }
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, ActRegister::class.java)
            startActivity(intent)
        }

        viewModelMain.stateLogin.observe(this) {
            when (it) {
                is LoginState.Loading -> {
                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setTitle("Login")
                    customAlert.setType(Type.PROGRESS)
                    customAlert.setMessage("Cargando")
                    customAlert.show()
                    println("Api:Cargando")
                }
                is LoginState.Successful -> {
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }
                    showAlert(Type.SUCCESS, "Exitoso", true)
                    println("Api:Exitoso")
                }
                is LoginState.Error -> {
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }
                    showAlert(Type.FAIL, it.message, false)
                    println("Api:Error${it.message}")
                }
            }
        }
    }

    private fun showAlert(type: IntArray, message: String, boolean: Boolean) {
        customAlert = CustomAlert(this, Theme.SYSTEM)
        customAlert.setTitle("Login")
        customAlert.setType(type)
        customAlert.setMessage(message)
        customAlert.setPositiveText(
            "Ok"
        ) {
            if (boolean) {
                val actDashboard = Intent(this, ActDashboard::class.java)
                actDashboard.putExtra("user", binding.etUser.text.toString())
                startActivity(actDashboard)
            }
            customAlert.dismiss()
        }
        customAlert.show()
    }

    private fun checkValidInput() =
        binding.etUser.text.toString().isNotEmpty() && binding.etPassword.text.toString()
            .isNotEmpty()

    private fun deleteError() {
        binding.inputUser.error = null
        binding.inputPassword.error = null
    }
}
