package com.example.animalsapp.models

data class animals(
    val description: String,
    val environmentId: String,
    val facts: List<String>,
    val id: String,
    val image: String,
    val imageGallery: List<String>,
    val name: String
)