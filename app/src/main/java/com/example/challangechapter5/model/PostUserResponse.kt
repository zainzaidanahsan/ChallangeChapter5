package com.example.challangechapter5.model


import com.google.gson.annotations.SerializedName

data class PostUserResponse(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("password")
    val password: String? = null,
    @field:SerializedName("username")
    val username: String? = null,
    @field:SerializedName("nama_lengkap")
    val namaLengkap: String? = null,
    @field:SerializedName("tanggal_lahir")
    val tanggalLahir: String? = null,
    @field:SerializedName("alamat")
    val alamat: String? = null,
)