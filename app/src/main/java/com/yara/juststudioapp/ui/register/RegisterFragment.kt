package com.yara.juststudioapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentRegisterBinding
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.util.Resource

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

        val navController = findNavController()
        var login = ""
        var password = ""

        // send register request
        binding.btnRegister.setOnClickListener {
            login = binding.tietEmail.text.toString()
            password = binding.tietPassword.text.toString()

            viewModel.register(UserLogin(login, password))
        }

        // handle register result
        viewModel.registerResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
            when (response) {
                is Resource.Success -> {
                    val bundle = Bundle();
                    bundle.putParcelable("user_login", UserLogin(login, password));
                    navController.popBackStack()
                    navController.navigate(R.id.confirmFragment, bundle)
                }

                is Resource.Error -> {
                }

                is Resource.Exception -> {
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
}