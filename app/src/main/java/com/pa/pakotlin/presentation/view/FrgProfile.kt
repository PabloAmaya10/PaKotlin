package com.pa.pakotlin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pa.pakotlin.InfoState
import com.pa.pakotlin.R
import com.pa.pakotlin.databinding.FrgProfileBinding
import com.pa.pakotlin.presentation.viewmodel.DashboardViewModel

class FrgProfile : Fragment() {
    private lateinit var binding: FrgProfileBinding
    private lateinit var viewModelProfile: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrgProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelProfile = requireActivity().run {
            ViewModelProvider(this)[DashboardViewModel::class.java]
        }

        viewModelProfile.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is InfoState.Successful -> {
                    binding.tvUserData.text = HtmlCompat.fromHtml(
                        getString(
                            R.string.data_user,
                            it.userModel.name,
                            it.userModel.lastName,
                            it.userModel.secondLastName,
                            it.userModel.birthday,
                            it.userModel.email,
                            it.userModel.gender,
                            it.userModel.state,
                            it.userModel.phone,
                            it.userModel.user
                        ),
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }
                else -> {}
            }
        }
    }
}
