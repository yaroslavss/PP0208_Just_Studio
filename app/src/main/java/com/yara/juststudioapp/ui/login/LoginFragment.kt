package com.yara.juststudioapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentLoginBinding
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.util.Constants.HTTP_ERROR_NOT_FOUND
import com.yara.juststudioapp.util.Constants.MSG_ERROR_HTTP_EXCEPTION
import com.yara.juststudioapp.util.Constants.MSG_LOGIN_ERROR_EMPTY_CREDENTIALS
import com.yara.juststudioapp.util.Constants.MSG_LOGIN_ERROR_HTTP_ERROR_NOT_FOUND
import com.yara.juststudioapp.util.Resource

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideErrorMessage()
        val navController = findNavController()

        // send auth request
        binding.btnLogin.setOnClickListener {
            hideErrorMessage()

            val login = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                showErrorMessage(MSG_LOGIN_ERROR_EMPTY_CREDENTIALS)
                return@setOnClickListener
            }

            // default login:
            // email = example1@ups.ru
            // password = Aa1234566_
            viewModel.signIn(UserLogin(login, password))
        }

        // go to register fragment
        binding.tvRegisterLink.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }

        // handle auth result
        viewModel.loginResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
            when (response) {
                is Resource.Success -> {
                    val sharedPreferences =
                        requireActivity().applicationContext.getSharedPreferences(
                            "settings",
                            Context.MODE_PRIVATE
                        )
                    sharedPreferences.edit().putString("API_TOKEN", response.data.token).apply()

                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        if (message == HTTP_ERROR_NOT_FOUND) {
                            showErrorMessage(MSG_LOGIN_ERROR_HTTP_ERROR_NOT_FOUND)
                        } else {
                            showErrorMessage(MSG_ERROR_HTTP_EXCEPTION)
                        }
                    }
                }

                is Resource.Exception -> {
                    showErrorMessage(MSG_ERROR_HTTP_EXCEPTION)
                }

                is Resource.Loading -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorMessage(message: String) {
        binding.tvErrorMsg.text = message
    }

    private fun hideErrorMessage() {
        binding.tvErrorMsg.text = ""
    }
}