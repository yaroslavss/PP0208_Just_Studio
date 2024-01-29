package com.yara.juststudioapp.ui.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yara.juststudioapp.R
import com.yara.juststudioapp.databinding.FragmentConfirmBinding
import com.yara.juststudioapp.domain.model.UserLogin
import com.yara.juststudioapp.domain.model.UserLoginWithCode

class ConfirmFragment : Fragment() {

    private var _binding: FragmentConfirmBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(ConfirmViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(
            activity,
            R.string.msg_confirm_code_sent,
            Toast.LENGTH_SHORT
        ).show()

        val userLogin = arguments?.getParcelable("user_login") as UserLogin?

        // send confirm request
        binding.btnConfirm.setOnClickListener {
            val login = userLogin?.emailUser as String
            val password = userLogin.password
            val code = binding.tietCode.text.toString()

            viewModel.confirm(UserLoginWithCode(login, password, code))
        }

        // handle register result
        viewModel.confirmResult.observe(viewLifecycleOwner) { response ->
            println("!!! $response")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}