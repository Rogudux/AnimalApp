package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animalsapp.screens.AnimalScreenDetail
import com.example.animalsapp.screens.EnvironmentScreen
import com.example.animalsapp.screens.AnimalsScreen
import com.example.animalsapp.screens.EnvironmentScreenDetail
import com.example.animalsapp.ui.theme.AnimalsAppTheme
import paw
import undefined

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AnimalsAppTheme {

                var selectedScreen by remember {
                    mutableStateOf("home")
                }

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.backGreen)),
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    bottomBar = {

                        NavigationBar(
                            containerColor = colorResource(id = R.color.componentsYellow)
                        ){

                            NavigationBarItem(
                                selected = selectedScreen == "home",
                                onClick = {selectedScreen = "home"
                                    navController.navigate("home")},
                                icon = {
                                    Icon(
                                        imageVector = undefined,
                                        contentDescription = "Home",
                                        tint = Color.Black,
                                        modifier  = Modifier.size(30.dp)
                                    )
                                    Text(text = "Animales", fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(start = 36.dp)
                                            .padding(top = 5.dp)
                                    )


                                },

                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = colorResource(id = R.color.componentsYellow)
                                )
                            )

                            NavigationBarItem(
                                selected = selectedScreen == "environment",
                                onClick = {selectedScreen = "environment"
                                    navController.navigate("environment")},
                                icon = {
                                    Icon(
                                        imageVector = paw,
                                        contentDescription = "environment",
                                        tint = Color.Black,
                                        modifier  = Modifier.size(30.dp),
                                    )
                                    Text(text = "Ambientes", fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(start = 35.dp)
                                            .padding(top = 5.dp)
                                    )

                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = colorResource(id = R.color.componentsYellow)
                                )
                            )

                        }

                    }


                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = "home"){
                        composable (route = "home"){
                            AnimalsScreen(paddingValues  = innerPadding, navController = navController)
                        }

                        composable(route = "animals/{id}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getString("id") ?: ""
                            AnimalScreenDetail(paddingValues = innerPadding, animalId = id)
                        }

                        composable (route = "environment"){
                            EnvironmentScreen(paddingValues  = innerPadding, navController = navController)
                        }

                        composable(route = "environments/{id}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getString("id") ?: ""
                            EnvironmentScreenDetail(innerPadding = innerPadding, environmentId = id)
                        }
                    }






                }
            }
        }
    }
}
