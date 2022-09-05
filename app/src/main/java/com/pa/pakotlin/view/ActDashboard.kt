package com.pa.pakotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pa.pakotlin.R
import com.pa.pakotlin.model.CredentialsModel

class ActDashboard : AppCompatActivity() {
    lateinit var credentialsModel: CredentialsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_dashboard)
        val bundle = intent.extras
        credentialsModel = CredentialsModel(
            bundle!!.getString("user").toString(),
            bundle!!.getString("password").toString()
        )
    }
}
