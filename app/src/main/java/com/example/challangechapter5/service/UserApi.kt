package com.example.challangechapter5.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApi {
    const val BASE_URL = "https://6345082c39ca915a69f48201.mockapi.io/"

    private val logging: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: RestfulUser by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(RestfulUser::class.java)
    }
}