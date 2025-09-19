package com.example.practicas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import java.text.DecimalFormat
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
import androidx.compose.ui.res.painterResource
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
                    color = Color.Magenta
                ) {
                    Greeting(
                    )
                }

            }
        }
    }

}

data class RangoISR(
    val limiteInferior: Double,
    val limiteSuperior: Double,
    val cuotaFija: Double,
    val tasa: Double
)

val rangosISR = listOf(
    RangoISR(7641.91,   15412.80,   809.25,    0.2136),
    RangoISR(15412.81,  24292.65,   2469.15,   0.2352),
    RangoISR(24292.66,  46378.50,   4557.75,   0.3000),
    RangoISR(46378.51,  61838.10,   11183.40,  0.3200),
    RangoISR(61838.11,  185514.30,  16130.55,  0.3400),
    RangoISR(185514.31, Double.MAX_VALUE, 58180.35, 0.3500)
)

fun calcularISRQuincenal(ingresoGravable: Double): Double {
    if (ingresoGravable <= 0.0) return 0.0
    val r = rangosISR.first { ingresoGravable >= it.limiteInferior && ingresoGravable <= it.limiteSuperior }
    val excedente = ingresoGravable - r.limiteInferior
    return r.cuotaFija + excedente * r.tasa
}

@Composable
fun Greeting() {
    val contexto = LocalContext.current
    var ingresoTexto by remember { mutableStateOf("") }
    var isrTexto by remember { mutableStateOf("") }
    var netoTexto by remember { mutableStateOf("") }

    val formato = remember { DecimalFormat("#,##0.00") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.satlogo),
                contentDescription = null
            )
        }
        Row(Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = ingresoTexto,
                label = { Text("Ingreso quincenal ($)") },
                onValueChange = { ingresoTexto = it },
                singleLine = true
            )
        }
        Row(
            Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = {

                    val ingreso = ingresoTexto.replace(",", "").toDoubleOrNull()
                    if (ingreso == null) {
                        Toast.makeText(contexto, "Ingrese una cantidad:", Toast.LENGTH_SHORT).show()
                        return@OutlinedButton
                    }
                    val isr = calcularISRQuincenal(ingreso)
                    val neto = ingreso - isr

                    isrTexto = formato.format(isr)
                    netoTexto = formato.format(neto)
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black)
            ) { Text("Calcular") }

            OutlinedButton(
                onClick = {
                    ingresoTexto = ""
                    isrTexto = ""
                    netoTexto = ""
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black)
            ) { Text("Borrar") }
        }

        Row(modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally)) {
            OutlinedTextField(
                value = isrTexto,
                label = { Text("ISR ($)") },
                onValueChange = { isrTexto = it },
                singleLine = true
            )
        }
        Row(modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally)) {
            OutlinedTextField(
                value = netoTexto,
                label = { Text("NETO ($)") },
                onValueChange = { netoTexto = it },
                singleLine = true
            )
        }
    }
}
