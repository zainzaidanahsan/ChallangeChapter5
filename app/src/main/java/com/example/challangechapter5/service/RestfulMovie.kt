package com.example.challangechapter5.service

import com.example.challangechapter5.model.GetDetailMovie
import com.example.challangechapter5.model.GetListMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestfulMovie {
    @GET("/3/movie/popular?api_key=04efbf06cd8d57a478d9101141a9a1e8")
    fun getMovieList(): Call<GetListMovie>

    @GET("/3/movie/{id}?api_key=04efbf06cd8d57a478d9101141a9a1e8")
    fun getMovieDetail(@Path("id") id: Int): Call<GetDetailMovie>
}