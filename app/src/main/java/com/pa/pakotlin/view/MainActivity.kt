package com.pa.pakotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.pa.pakotlin.data.network.RetrofitClient
import com.pa.pakotlin.databinding.ActivityMainBinding
import com.pa.pakotlin.model.ErrorLogin
import com.pa.pakotlin.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModelMain: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
//            deleteError()
//            viewModelMain.login(
//                CredentialsModel(
//                    binding.etUser.text.toString(),
//                    binding.etPassword.text.toString()
//                )
//            )
            lifecycleScope.launch {
                val response = RetrofitClient.api().getPokemon(1)
                println("Response: $response")
            }
        }
        viewModelMain.credentials.observe(this) {
            val actDashboard = Intent(this, ActDashboard::class.java)
            actDashboard.putExtra("user", it.user)
            actDashboard.putExtra("password", it.password)
            startActivity(actDashboard)
        }
        viewModelMain.errorLogin.observe(this) {
            showError(it)
        }
    }

    private fun showError(errorLogin: ErrorLogin) {
        if (errorLogin == ErrorLogin.USER_ERROR || errorLogin == ErrorLogin.USER_EMPTY) {
            binding.inputUser.error = errorLogin.error
        } else if (errorLogin == ErrorLogin.PASSWORD_ERROR || errorLogin == ErrorLogin.PASSWORD_EMPTY) {
            binding.inputPassword.error = errorLogin.error
        }
    }

    private fun deleteError() {
        binding.inputUser.error = null
        binding.inputPassword.error = null
    }
}
