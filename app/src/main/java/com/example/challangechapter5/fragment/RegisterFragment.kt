package com.example.challangechapter5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challangechapter5.R
import com.example.challangechapter5.databinding.FragmentRegisterBinding
import com.example.challangechapter5.viewModel.UserViewModel

class RegisterFragment : Fragment() {

     lateinit var binding: FragmentRegisterBinding
     lateinit var userVM: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            daftar()
        }
    }

    private fun daftar() {
        val username = binding.etUsernameRegister.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val password = binding.etPasswordRegister.text.toString()
        val passwordConfirm = binding.etKonfirmPasswordRegister.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            if (password == passwordConfirm) {
                userVM.callPostApiUser(username, email, password)
                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFfragment)

            } else {
                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
