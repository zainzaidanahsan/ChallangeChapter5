package com.example.challangechapter5.service

import com.example.challangechapter5.model.Datauser
import com.example.challangechapter5.model.GetResponseUserItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulUser {
    @GET("users")
    fun getAllUser(): Call<List<GetResponseUserItem>>

    @POST("users")
    fun registerUser(@Body request: Datauser): Call<GetResponseUserItem>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id : Int, @Body request: Datauser): Call<GetResponseUserItem>
}