package com.yara.juststudioapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentLoginBinding
import com.yara.juststudioapp.domain.model.UserLogin

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

        // send auth request
        binding.btnLogin.setOnClickListener {
            val login = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    activity,
                    R.string.msg_empty_auth,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // dummy login:
            // email = example1@ups.ru
            // password = Aa1234566_
            viewModel.signIn(UserLogin(login, password))
        }

        viewModel.token.observe(viewLifecycleOwner) { token ->
            println("!!! $token")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}