package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pa.pakotlin.databinding.FrgAboutBinding

class FrgAbout : Fragment() {
    private lateinit var binding: FrgAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FrgAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAboutA.setOnClickListener {
            val actionWithParameters =
                FrgAboutDirections.toFrgAboutB("Hola,esto es un parametro", "Argumento 2")
            findNavController().navigate(actionWithParameters)
        }
    }
}
