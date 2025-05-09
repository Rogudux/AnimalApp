package com.example.animalsapp.services

import com.example.animalsapp.models.animals
import com.example.animalsapp.models.environment
import retrofit2.http.GET

interface EnvironmentService {
        @GET("environments")

        suspend fun getEnvironment():
                List<environment>

}