package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animalsapp.R
import com.example.animalsapp.components.AnimalIcon
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


    environment.let { environment ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
            ,horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Row (modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Ambientes", fontSize = 35.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 110.dp, bottom = 5.dp)
                )


            }



            Column (modifier = Modifier
                .padding(2.dp),
                verticalArrangement = Arrangement
                    .spacedBy(30.dp)
            ){
                environment.forEach{environment ->

                    AnimalIcon(
                        image = environment.image,
                        name = environment.name
                    )
                }
            }








        }



    }







}









