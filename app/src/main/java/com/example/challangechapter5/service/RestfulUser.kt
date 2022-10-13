package com.example.challangechapter5.service

import com.example.challangechapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface RestfulUser {
    @GET("users")
    fun getAllUser(): Call<List<GetResponseUserItem>>

    @POST("users")
    fun registerUser(@Body request: Datauser): Call<PostResponseUser>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body request: DataProfile): Call<PostResponseUser>
}