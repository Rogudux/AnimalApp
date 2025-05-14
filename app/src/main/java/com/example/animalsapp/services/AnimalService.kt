package com.example.animalsapp.services

import com.example.animalsapp.models.animals
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalService {
    @GET("animals")
    suspend fun getAnimals():
            List<animals>

    @GET("animals/{id}")
    suspend fun getAnimalsById(@Path("id")id:String): animals



}
