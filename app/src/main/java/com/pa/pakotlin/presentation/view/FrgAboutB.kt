package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pa.pakotlin.databinding.FrgAboutBBinding

class FrgAboutB : Fragment() {
    private val args: FrgAboutBArgs by navArgs()
    lateinit var binding: FrgAboutBBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FrgAboutBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Argumento1 ${args.argFromA}")
        println("Argumento2 ${args.argFromA2}")
        val actionWithParameters =
            FrgAboutBDirections.toFrgAboutC("Hola,esto es un parametro", 10, true)
        findNavController().navigate(actionWithParameters)
        binding.btnAboutB.setOnClickListener {
            findNavController().navigate(actionWithParameters)
        }
    }
}
