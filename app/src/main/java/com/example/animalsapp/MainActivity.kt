package com.example.animalsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animalsapp.screens.EnvironmentScreen
import com.example.animalsapp.screens.HomeScreen
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
                    modifier = Modifier.fillMaxSize()
                        .background(colorResource(id = R.color.backGreen)),
                    contentColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    bottomBar = {

                        NavigationBar(
                            containerColor = colorResource(id = R.color.componentsYellow).copy(alpha = 0.1f)
                        ){

                            NavigationBarItem(
                                selected = selectedScreen == "home",
                                onClick = {selectedScreen = "home"
                                    navController.navigate("home")},
                                icon = {
                                    Icon(
                                        imageVector = undefined,
                                        contentDescription = "Home",
                                        tint = Color.White,
                                        modifier  = Modifier.size(30.dp),
                                    )

                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White.copy(alpha = 0.2f)
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
                                        tint = Color.White,
                                        modifier  = Modifier.size(30.dp),
                                    )

                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.White.copy(alpha = 0.2f)
                                )
                            )

                        }

                    }





                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = "home"){
                        composable (route = "home"){
                            HomeScreen(paddingValues  = innerPadding)
                        }
                        composable (route = "environment"){
                            EnvironmentScreen(paddingValues  = innerPadding)
                        }
                    }






                }
            }
        }
    }
}
