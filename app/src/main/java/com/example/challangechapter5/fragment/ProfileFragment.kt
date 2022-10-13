package com.example.challangechapter5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.challangechapter5.R
import com.example.challangechapter5.databinding.FragmentProfileBinding
import com.example.challangechapter5.utils.Constant
import com.example.challangechapter5.utils.PreferencesHelper
import com.example.challangechapter5.viewModel.UserViewModel

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var userVM: UserViewModel
    lateinit var sharedPreferences: PreferencesHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = PreferencesHelper(requireContext())
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        update()
        logout()
    }

    private fun update(){
        binding.btnUpdate.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val namaLengkap = binding.etNamaLengkap.text.toString().trim()
            val tanggalLahir = binding.etTanggalLahir.text.toString().trim()
            val alamat = binding.etAlamat.text.toString().trim()
            sharedPreferences.put(Constant.PREF_USERNAME, username)
            userVM.updatePutApiUser(sharedPreferences.getInt(Constant.PREF_USER_ID), username, namaLengkap, tanggalLahir, alamat)
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT)
                .show()
            it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }

    private fun logout(){
        binding.btnLogout.setOnClickListener {
            sharedPreferences.clear()
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT)
                .show()
            it.findNavController().navigate(R.id.action_profileFragment_to_loginFfragment)
        }
    }

}