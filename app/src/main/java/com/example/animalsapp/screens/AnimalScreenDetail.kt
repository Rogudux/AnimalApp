package com.example.animalsapp.screens

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import com.example.animalsapp.components.AnimalIcon
import com.example.animalsapp.components.FactsItem
import com.example.animalsapp.components.GalleryItem
import com.example.animalsapp.models.animals
import com.example.animalsapp.services.AnimalService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AnimalScreenDetail(
    paddingValues: PaddingValues,
    animalId :String
){

    var animals by remember {
        mutableStateOf<animals?>(null)
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
                val productService = retrofitBuilder.create(AnimalService::class.java)
                val response = productService.getAnimalsById(animalId)
                animals = response
                Log.i("AnimalScreenDetail", response.toString())
            }catch (e:Exception){
                Log.e("AnimalScreenDetail",e.toString())
            }


        }

    }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Log.i("Animal Screen " , animals?.name ?: "")


            Row (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = animals?.name ?: "Nombre no disponible",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            Column(
                modifier = Modifier.padding(2.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {

                Box(modifier = Modifier
                    .width(350.dp)
                    .height(250.dp)

                ){
                    AsyncImage(
                        model = animals?.image,
                        contentDescription = null,
                        placeholder = painterResource(R.drawable.ic_launcher_background),
                        error =  painterResource(R.drawable.ic_launcher_background),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Row (modifier = Modifier.fillMaxWidth()){
                    Text(
                        text = animals?.description ?: "descripcion no disponible",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }

                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Hechos Interesantes",
                        fontSize = 40.sp,
                        color = colorResource(id = R.color.componentsYellow),
                        fontWeight = FontWeight.Bold)
                }

                Column (modifier = Modifier.padding(2.dp),
                    verticalArrangement = Arrangement
                        .spacedBy(12.dp)
                ){
                    animals?.facts?.forEach{
                        FactsItem(
                            icon = Icons.Default.Info,
                            text = it,
                        )
                    }
                }

                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Galeria de fotos",
                        fontSize = 40.sp,
                        color = colorResource(id = R.color.componentsYellow),
                        fontWeight = FontWeight.Bold)
                }

                Column (modifier = Modifier.padding(2.dp),
                    verticalArrangement = Arrangement
                        .spacedBy(12.dp)
                ){
                    animals?.imageGallery?.let {
                        GalleryItem(
                            imageGallery = it
                        )
                    }
                }
            }


        }








        }
