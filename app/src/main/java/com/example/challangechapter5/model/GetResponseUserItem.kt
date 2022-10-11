package com.example.challangechapter5.model


import com.google.gson.annotations.SerializedName

data class GetResponseUserItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)