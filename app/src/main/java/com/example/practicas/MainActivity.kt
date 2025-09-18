package com.example.practicas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    color = Color.Green
                ) {
                    Greeting(
                    )
                }

            }
        }
    }

}

@Composable
fun Greeting() {
    val context= LocalContext.current
    var Valor1 by remember { mutableStateOf("") }
    var Valor2 by remember { mutableStateOf("") }
    var Resultado by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "Victor Hugo G",
            style = MaterialTheme.typography.bodySmall
        )
        Row(Modifier.padding(20.dp)){
            OutlinedTextField(
                value=Valor1,
                label={Text("Valor 1:")},
                onValueChange ={Valor1=it}
            )
        }
        Row(
            Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val a = Valor1.toInt()
                    val b = Valor2.toInt()
                    val c = a + b
                    Resultado = c.toString()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Transparent
                )
            ) {
                Text(text = "Suma")
            }

            OutlinedButton(
                onClick = {
                    Resultado = ""
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Blue
                )
            ) {
                Text(text = "BORRAR")
            }
        }

        Row(
            Modifier.align(Alignment.CenterHorizontally)
        ) {

        }
        Row(
            modifier= Modifier.padding(20.dp).
            align(Alignment.CenterHorizontally)
        ){
            OutlinedTextField(
                value = Valor2,
                label={Text("Valor 2:")},
                onValueChange ={Valor2=it}
            )
        }
        Row(
            modifier= Modifier.padding(20.dp).
            align(Alignment.CenterHorizontally)
        ){
            OutlinedTextField(
                value = Resultado,
                label={Text("Resultado:")},
                onValueChange ={Resultado=it}
            )
        }
    }
}