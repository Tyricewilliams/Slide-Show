package com.example.slideshow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class Slide(val imageRes: Int, val caption: String)

private val slides = listOf(
    Slide(R.drawable.pic1, "Karma and Sandy"),
    Slide(R.drawable.pic2, "Cutie karma"),
    Slide(R.drawable.pic3, "karma and sandy in sweaters"),
    Slide(R.drawable.pic4, "Comfy sandy"),
    Slide(R.drawable.pic5, "Comfy sandy and karma")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SlideshowScreen()
            }
        }
    }
}

@Composable
fun SlideshowScreen() {
    var currentIndex by rememberSaveable { mutableIntStateOf(0) }
    var input by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    val slide = slides[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = slide.imageRes),
            contentDescription = slide.caption,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = slide.caption, style = MaterialTheme.typography.titleMedium)
        Text(text = "Picture ${currentIndex + 1} of ${slides.size}")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                currentIndex = (currentIndex - 1 + slides.size) % slides.size
                errorMessage = null
            }) { Text("Back") }

            Button(onClick = {
                currentIndex = (currentIndex + 1) % slides.size
                errorMessage = null
            }) { Text("Next") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Picture number (1-${slides.size})") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = errorMessage != null,
            supportingText = { errorMessage?.let { Text(it) } }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val number = input.trim().toIntOrNull()
            if (number != null && number in 1..slides.size) {
                currentIndex = number - 1
                errorMessage = null
            } else {
                errorMessage = "Enter a whole number from 1 to ${slides.size}"
            }
        }) { Text("Go") }
    }
}