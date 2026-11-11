package com.example.slideshow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

private val slides = listOf(
    Slide(R.drawable.pic1, "Karma and Sandy"),
    Slide(R.drawable.pic2, "Cutie karma"),
    Slide(R.drawable.pic3, "karma and sandy in sweaters"),
    Slide(R.drawable.pic4, "Comfy sandy"),
    Slide(R.drawable.pic5, "Comfy sandy and karma"),
    Slide(R.drawable.pic6, "Trust No One"),
   Slide(R.drawable.pic7, "the Great Outdoors"),
   Slide(R.drawable.pic8, "Sunny Day"),
   Slide(R.drawable.pic9, "Baby Ty"),
   Slide(R.drawable.pic10, "Great Time")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SlideList(slides)
            }
        }
    }
}

@Composable
fun SlideList(slides: List<Slide>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(slides) { slide ->
            SlideRow(slide)
        }
    }
}

@Composable
fun SlideRow(slide: Slide) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = slide.imageRes),
                contentDescription = slide.caption,
                modifier = Modifier.size(96.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = slide.caption,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}