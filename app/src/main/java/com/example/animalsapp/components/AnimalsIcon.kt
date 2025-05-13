package com.example.animalsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.animalsapp.R

@Composable
fun AnimalIcon(image: String, name: String){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier
            .width(200.dp)
            .height(200.dp)

        ){
            AsyncImage(
                model = image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error =  painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier.fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                )
        }

        Text(text = name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )



    }
}