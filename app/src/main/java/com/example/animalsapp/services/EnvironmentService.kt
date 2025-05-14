package com.example.animalsapp.services

import com.example.animalsapp.models.animals
import com.example.animalsapp.models.environment
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EnvironmentService {
        @GET("environments")

        suspend fun getEnvironment():
                List<environment>

        @GET("environments/{id}")
        suspend fun getEnvironmentsById(@Path("id") id: String): environment

        @GET("animals")
        suspend fun getAnimalsByEnvironment(@Query("environmentId") environmentId: String): List<animals>

}