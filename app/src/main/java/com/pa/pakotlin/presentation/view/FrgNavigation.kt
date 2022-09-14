package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.FrgNavigationBinding

class FrgNavigation : Fragment() {
    private lateinit var binding: FrgNavigationBinding
    private lateinit var botomnavigation: BottomNavigationView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = binding.materialToolBar
        toolbar.setTitle(R.string.app_name)
        changeFragment(FrgNavigationHome())
        botomnavigation = binding.bottomNavigation
        botomnavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> changeFragment(FrgNavigationHome())
                R.id.item_notification -> changeFragment(FrgNavigationProfile())
                R.id.item_perfil -> changeFragment(FrgNavigationNotification())
                else -> {
                    changeFragment(FrgNavigationHome())
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        val fragmentTransaccion: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaccion.replace(R.id.container, fragment)
        fragmentTransaccion.commit()
    }
}
