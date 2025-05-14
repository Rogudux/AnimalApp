package com.example.animalsapp.components

import android.widget.Gallery
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.animalsapp.R

@Composable
fun GalleryItem(imageGallery: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(12.dp)
    ) {
        AsyncImage(
            model = imageGallery[currentIndex],
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Anterior",
                color = Color.White,
                modifier = Modifier
                    .clickable {
                        if (currentIndex > 0) currentIndex--
                    }
                    .padding(8.dp)
            )
            Text(
                text = "Siguiente",
                color = Color.White,
                modifier = Modifier
                    .clickable {
                        if (currentIndex < imageGallery.size - 1) currentIndex++
                    }
                    .padding(8.dp)
            )
        }
    }
}