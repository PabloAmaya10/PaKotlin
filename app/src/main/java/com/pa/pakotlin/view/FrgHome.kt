package com.pa.pakotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.FrgHomeBinding

class FrgHome : Fragment() {

    private lateinit var binding: FrgHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(R.id.toFrgProfile)
        }
        binding.btnContent.setOnClickListener {
            findNavController().navigate(R.id.toFrgContent)
        }
        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.toFrgAbout)
        }
    }
}
