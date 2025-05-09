package com.example.animalsapp.services

import com.example.animalsapp.models.animals
import retrofit2.http.GET

interface AnimalService {
    @GET("animals")

    suspend fun getAnimals():
            List<animals>
}