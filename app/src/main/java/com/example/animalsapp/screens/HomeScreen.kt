package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.animalsapp.models.animals
import com.example.animalsapp.services.AnimalService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun HomeScreen(paddingValues: PaddingValues){

    var animals by remember {
        mutableStateOf<List<animals>>(emptyList())
    }

    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    LaunchedEffect(key1 = true) {

        try {
            scope.launch{
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(AnimalService::class.java)
                animals = service.getAnimals()
                println(animals.toString())
            }

        }
        catch (e:Exception){

            Log.e("HomeScreen",e.toString())

        }

    }









}