package com.example.challangechapter5.model

import com.google.gson.annotations.SerializedName

data class GetListMovie(
    @SerializedName("results")
    val movies: List<DataMovie>
)
