package com.example.challangechapter5.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.challangechapter5.R
import com.example.challangechapter5.databinding.FragmentLoginFfragmentBinding
import com.example.challangechapter5.model.GetResponseUserItem
import com.example.challangechapter5.service.UserApi
import com.example.challangechapter5.utils.Constant
import com.example.challangechapter5.utils.PreferencesHelper
import com.example.challangechapter5.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFfragment : Fragment() {


     lateinit var binding: FragmentLoginFfragmentBinding
     lateinit var userVM: UserViewModel
     lateinit var sharedPreferences: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPreferences = PreferencesHelper(requireContext())
        binding = FragmentLoginFfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toRegister()
        toLogin()
    }

    private fun toLogin() {
        binding.buttonLogin.setOnClickListener {
            authentification(
                binding.etUsernameRegister.text.toString().trim(),
                binding.etPasswordRegister.text.toString().trim()
            )
        }
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferences.getBoolean(Constant.PREF_IS_LOGIN)) {
            findNavController().navigate(R.id.action_loginFfragment_to_homeFragment)
        }
    }

    private fun authentification(username: String, password: String) {
        UserApi.instance.getAllUser()
            .enqueue(object : Callback<List<GetResponseUserItem>> {
                override fun onResponse(
                    call: Call<List<GetResponseUserItem>>,
                    response: Response<List<GetResponseUserItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.d(ContentValues.TAG, "onResponse: ${responseBody.toString()}")
                            for (i in responseBody.indices) {
                                if (responseBody[i].username.equals(
                                        username,
                                        ignoreCase = false
                                    ) && responseBody[i].password.equals(
                                        password, ignoreCase = false
                                    )
                                ) {
                                    sharedPreferences.put(
                                        Constant.PREF_USERNAME,
                                        responseBody[i].username.toString()
                                    )
                                    sharedPreferences.put(
                                        Constant.PREF_USER_ID,
                                        responseBody[i].id!!.toInt()
                                    )
                                    sharedPreferences.put(Constant.PREF_IS_LOGIN, true)
                                    Toast.makeText(
                                        requireContext(),
                                        "Login Berhasil",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    findNavController().navigate(R.id.action_loginFfragment_to_homeFragment)
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Login Gagal",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetResponseUserItem>>, t: Throwable) {
                    Toast.makeText(requireContext(), "Something Wrong", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun toRegister() {
        binding.textBpa.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFfragment_to_registerFragment)
        }
    }
}