package com.yara.juststudioapp.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentProfileBinding
import com.yara.juststudioapp.util.Constants.HTTP_ERROR_UNAUTHORIZED
import com.yara.juststudioapp.util.Resource

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val sharedPreferences =
            requireActivity().applicationContext.getSharedPreferences(
                "settings",
                Context.MODE_PRIVATE
            )

        val token = sharedPreferences.getString("API_TOKEN", "")

        println("!!! profile: $token")

        token?.let {
            if (it.isEmpty()) {
                navController.navigate(R.id.action_profileFragment_to_loginFragment)
            }
            viewModel.getUserInfo(token)
        }

        viewModel.userResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
            when (response) {
                is Resource.Success -> {
                    val tietEmail = binding.tietEmail as TextView
                    tietEmail.text = response.data.emailUser
                    val tietUserName = binding.tietUserName as TextView
                    tietUserName.text = response.data.userName
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        if (message == HTTP_ERROR_UNAUTHORIZED) {
                            navController.navigate(R.id.loginFragment)
                        }
                    }
                }

                is Resource.Exception -> {}
                is Resource.Loading -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}