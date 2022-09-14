package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.view.contains
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.mh.custom_alert.CustomAlert
import com.mh.custom_alert.Theme
import com.mh.custom_alert.Type
import com.pa.pakotlin.InfoState
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.ActDashboardBinding
import com.pa.pakotlin.presentation.viewmodel.DashboardViewModel

class ActDashboard : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var customAlert: CustomAlert
    private lateinit var binding: ActDashboardBinding
    private val viewModelDashboard: DashboardViewModel by viewModels()
    lateinit var actionBarDrawableToggle: ActionBarDrawerToggle
    private var user = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras != null) {
            val bundle = intent.extras
            user = bundle!!.getString("user").toString()
            viewModelDashboard.saveSharedPref(bundle!!.getString("user").toString(), this)
            viewModelDashboard.stateSp.observe(this) {
                if (it) {
                    binding = ActDashboardBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    setNavigationView()
                    setDrawerLayout()
                }
            }
        } else {
            binding = ActDashboardBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setNavigationView()
            setDrawerLayout()
        }
    }

    private fun setDrawerLayout() {
        setSupportActionBar(binding.topAppBar)
        actionBarDrawableToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        actionBarDrawableToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun setNavigationView() {
        viewModelDashboard.getUserInfo(user, this)
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val viewHeader: View = LayoutInflater.from(this)
            .inflate(R.layout.layout_dashboard_header, navigationView, false)
        navigationView.addHeaderView(viewHeader)
        viewModelDashboard.userInfo.observe(this) {
            when (it) {
                is InfoState.Successful -> {
                    val textName: TextView = findViewById(R.id.tvRealNameDas)
                    val textEmail: TextView = findViewById(R.id.tvRealEmailDas)
                    val textUser: TextView = findViewById(R.id.tvRealUserDas)
                    val constraintLayoutView: ConstraintLayout = findViewById(R.id.cL)
                    val constraintLayoutShi: ConstraintLayout = findViewById(R.id.clShi)
                    textName.text = it.userModel.name
                    textUser.text = it.userModel.user
                    textEmail.text = it.userModel.email
                    constraintLayoutView.isVisible = true
                    constraintLayoutShi.isVisible = false
                }
                else -> {}
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home -> {
                val navOptions: NavOptions =
                    NavOptions.Builder().setPopUpTo(R.id.frgHome, true).build()
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgHome, null, navOptions)
                /*Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgHome)*/
            }
            R.id.menu_profile -> {
                val bundle = bundleOf("user" to user)
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgProfile, bundle)
            }
            R.id.menu_content -> {
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgContent)
            }
            R.id.menu_about -> {
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgAbout)
            }
            R.id.menu_navigation -> {
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgNavigation)
            }
            R.id.menu_pokemon -> {
                Navigation.findNavController(this, R.id.navHome)
                    .navigate(R.id.frgPokemon)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawableToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val numberFragments =
            supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount!!
        if (numberFragments == 0) {
            customAlert = CustomAlert(this, Theme.SYSTEM)
            customAlert.setTitle("Home")
            customAlert.setType(Type.WARNING)
            customAlert.setMessage("Estas seguro de salir de la app!")
            customAlert.setPositiveText(
                "Ok"
            ) {
                customAlert.dismiss()
                finish()
            }
            customAlert.setNegativeText(
                "No"
            ) {
                customAlert.dismiss()
            }
            customAlert.show()
        } else {
            super.onBackPressed()
        }
    }
}
