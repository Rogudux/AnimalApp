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
import com.example.animalsapp.models.environment
import com.example.animalsapp.services.EnvironmentService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun EnvironmentScreen(paddingValues: PaddingValues){

    var environment by remember {
        mutableStateOf<List<environment>>(emptyList())
    }

    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    LaunchedEffect(key1 = true) {

        try {
            scope.launch {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service = retrofit.create(EnvironmentService::class.java)
                environment = service.getEnvironment()


            }
        }
        catch (e:Exception){

            Log.e("EnvironmentScreen",e.toString())

        }
    }







}









