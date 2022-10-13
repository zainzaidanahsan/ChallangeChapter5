package com.example.challangechapter5.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangechapter5.model.DataProfile
import com.example.challangechapter5.model.Datauser
import com.example.challangechapter5.model.PostResponseUser
import com.example.challangechapter5.service.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    lateinit var postLDUser: MutableLiveData<PostResponseUser?>
    lateinit var updateUser: MutableLiveData<PostResponseUser?>

    init {
        postLDUser = MutableLiveData()
        updateUser = MutableLiveData()
    }

    fun postLiveDataUser(): MutableLiveData<PostResponseUser?> {
        return postLDUser
    }

    fun callPostApiUser(username: String, email: String, password: String) {
        UserApi.instance.registerUser(
            Datauser(username, email, password)
        )
            .enqueue(object : retrofit2.Callback<PostResponseUser> {
                override fun onResponse(
                    call: retrofit2.Call<PostResponseUser>,
                    response: retrofit2.Response<PostResponseUser>
                ) {
                    if (response.isSuccessful) {
                        postLDUser.postValue(response.body())
                    } else {
                        Log.d("Error", response.message())
                        postLDUser.postValue(null)
                    }
                }

//                override fun onFailure(call: retrofit2.Call<PostUserResponse>, t: Throwable) {
//
//                }

                override fun onFailure(call: Call<PostResponseUser>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                    postLDUser.postValue(null)
                }

            })
    }

    fun updatePutApiUser(
        id: Int,
        username: String,
        namaLengkap: String,
        tanggalLahir: String,
        alamat: String
    ) {
        UserApi.instance.updateUser(id, DataProfile(username, namaLengkap, tanggalLahir, alamat))
            .enqueue(object :
                Callback<PostResponseUser> {
                override fun onResponse(
                    call: Call<PostResponseUser>,
                    response: Response<PostResponseUser>
                ) {
                    if (response.isSuccessful) {
                        updateUser.postValue(response.body())
                    } else {
                        updateUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<PostResponseUser>, t: Throwable) {
                    updateUser.postValue(null)
                }

            })
    }

}