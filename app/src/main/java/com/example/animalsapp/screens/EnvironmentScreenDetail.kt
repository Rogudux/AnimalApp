package com.example.animalsapp.screens

import android.os.Environment
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.animalsapp.R
import com.example.animalsapp.models.animals
import com.example.animalsapp.models.environment
import com.example.animalsapp.services.AnimalService
import com.example.animalsapp.services.EnvironmentService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun EnvironmentScreenDetail(
    innerPadding: PaddingValues,
    environmentId :String,

){

    var environment by remember {
        mutableStateOf<environment?>(null)
    }

    var animals by remember {
        mutableStateOf<List<animals>>(emptyList())
    }


    val scope = rememberCoroutineScope()
    val BASE_URL = "https://animals.juanfrausto.com/api/"

    LaunchedEffect(key1 = true) {
        scope.launch {
            try {
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val productService = retrofitBuilder.create(EnvironmentService::class.java)
                val response1 = productService.getEnvironmentsById(environmentId)
                environment = response1

                val response2 = productService.getAnimalsByEnvironment(environmentId)
                animals = response2

                Log.d("EnvironmentScreenDetail", "environmentId: $environmentId")


            }catch (e:Exception){
                Log.e("EnvironmentScreenDetail",e.toString())
            }


        }

    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Column(
            modifier = Modifier.padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(250.dp)

            ) {
                AsyncImage(
                    model = environment?.image,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    error = painterResource(R.drawable.ic_launcher_background),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
        }

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = environment?.name ?: "Nombre no disponible",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }



        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = environment?.description ?: "Nombre no disponible",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            animals.forEach { animal ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = animal.image,
                            contentDescription = null,
                            placeholder = painterResource(R.drawable.ic_launcher_background),
                            error = painterResource(R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(
                        text = animal.name.take(20),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }





    }
}