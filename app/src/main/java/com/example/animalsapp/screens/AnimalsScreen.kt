package com.example.animalsapp.screens

import android.R.attr.text
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.animalsapp.R
import com.example.animalsapp.components.AnimalIcon
import com.example.animalsapp.models.animals
import com.example.animalsapp.services.AnimalService
import kotlinx.coroutines.launch
import plant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun AnimalsScreen(paddingValues: PaddingValues, navController: NavController){

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

            Log.e("AnimalsScreen",e.toString())

        }

    }

    animals.let { animal ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
            ,horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Row (modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Animales", fontSize = 35.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(3f)
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.componentsYellow),
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(2f)
                ) {
                    Icon(
                        imageVector = plant,
                        contentDescription = "plant",
                        tint = Color.Black,
                        modifier  = Modifier
                            .size(28.dp)

                    )
                    Text(text = "Agregar",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize =18.sp,
                        modifier = Modifier
                            .padding(start = 10.dp))
                }

            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Conoce a los animales más increíbles del mundo",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 6.dp,bottom = 10.dp))


            }

            Column(
                modifier = Modifier.padding(2.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                animal.forEach { animal ->
                    AnimalIcon(
                       animal = animal,
                        onAnimalClick = { clickedAnimal ->
                            Log.i("Animal Screen " , clickedAnimal.id)
                            navController.navigate("animals/${clickedAnimal.id}")

                        }
                    )
                }
            }








        }






    }











}