package com.yara.juststudioapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yara.juststudioapp.databinding.FragmentRegisterBinding
import com.yara.juststudioapp.domain.model.UserLogin

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // send register request
        binding.btnRegister.setOnClickListener {
            val login = binding.tietEmail.text.toString()
            val password = binding.tietPassword.text.toString()

            viewModel.register(UserLogin(login, password))
        }

        // handle register result
        viewModel.registerResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}