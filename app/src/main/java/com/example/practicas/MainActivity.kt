package com.example.practicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicas.ui.theme.PracticasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) }
            Datos (
                nombre = "Victor Hugo G",
            )
        }
     }
   }

@Composable
fun Datos(
    nombre : string,
    modifier: Modifier = Modifier
){
    Text(
        text = nombre,
        fontSize = 40.sp,
        color = Color(0xFF40E0D0)

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNameAndControl(){
    PracticasTheme {
        com.example.practicas.Datos(
            nombre = "Victor Hugo G",
        )
    }
}