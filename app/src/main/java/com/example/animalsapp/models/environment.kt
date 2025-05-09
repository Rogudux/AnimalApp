package com.example.animalsapp.models

import com.google.gson.annotations.SerializedName

data class environment(
    @SerializedName("_id") val _id: String,
    val description: String,
    val image: String,
    val name: String
)