package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.FrgAboutCBinding

class FrgAboutC : Fragment() {
    private val args: FrgAboutCArgs by navArgs()
    private lateinit var binding: FrgAboutCBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FrgAboutCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Argumento1 ${args.argFromB}")
        println("Argumento2 ${args.argFromBInteger}")
        println("Argumento3 ${args.argFromBBoolean}")
        binding.btnAboutC.setOnClickListener {
            findNavController().navigate(R.id.toFrgAbout)
        }
    }
}
