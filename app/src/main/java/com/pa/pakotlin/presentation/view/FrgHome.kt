package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pa.pakotlin.InfoState
import com.pa.pakotlin.databinding.FrgHomeBinding
import com.pa.pakotlin.presentation.viewmodel.DashboardViewModel

class FrgHome : Fragment() {

    private lateinit var binding: FrgHomeBinding
    private lateinit var viewModelHome: DashboardViewModel
    // private val viewModelHome: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrgHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelHome = requireActivity().run {
            ViewModelProvider(this)[DashboardViewModel::class.java]
        }
        /*viewModelHome.gatUserSharedPref(requireContext())
        viewModelHome.user.observe(viewLifecycleOwner) {
            binding.tvUserNme.text = it
        }*/
        viewModelHome.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is InfoState.Successful -> {
                    binding.tvUserNme.text = it.userModel.name
                }
                else -> {}
            }
        }
    }
}
