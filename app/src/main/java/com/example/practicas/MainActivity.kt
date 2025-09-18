package com.example.practicas

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge
        setContent {
            CalculadoraSimple()
        }
    }
}

@Composable
fun CalculadoraSimple() {
    var textoPantalla by remember { mutableStateOf("0") }
    var numeroGuardado by remember { mutableStateOf(0.0) }
    var operacion by remember { mutableStateOf<String?>(null) }
    var limpiarPantalla by remember { mutableStateOf(false) }


    val fondo = Color.Red
    val colorNumero = Color.Transparent
    val colorOperador = Color.Black
    val colorAccion = Color.Black
    val colorIgual = Color.Black


    fun escribirNumero(num: String) {
        if (textoPantalla == "0" || limpiarPantalla) {
            textoPantalla = num
            limpiarPantalla = false
        } else {
            textoPantalla += num
        }
    }

    fun limpiar() {
        textoPantalla = "0"
        numeroGuardado = 0.0
        operacion = null
        limpiarPantalla = false
    }

    fun borrar() {
        textoPantalla = if (textoPantalla.length > 1) {
            textoPantalla.dropLast(1)
        } else "0"
    }

    fun elegirOperacion(op: String) {
        numeroGuardado = textoPantalla.toDouble()
        operacion = op
        limpiarPantalla = true
    }

    fun calcular() {
        val segundo = textoPantalla.toDouble()
        val resultado = when (operacion) {
            "+" -> numeroGuardado + segundo
            "-" -> numeroGuardado - segundo
            "*" -> numeroGuardado * segundo
            "/" -> if (segundo != 0.0) numeroGuardado / segundo else Double.NaN
            else -> segundo
        }
        textoPantalla = if (resultado.isNaN()) "Error" else resultado.toString()
        numeroGuardado = resultado
        operacion = null
        limpiarPantalla = true
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = textoPantalla,
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }


        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotonCalc("AC", colorAccion, Color.White, { limpiar() }, Modifier.weight(1f))
                BotonCalc("⌫", colorAccion, Color.White, { borrar() }, Modifier.weight(1f))
                BotonCalc("/", colorOperador, Color.White, { elegirOperacion("/") }, Modifier.weight(1f))
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotonCalc("7", colorNumero, Color.White, { escribirNumero("7") }, Modifier.weight(1f))
                BotonCalc("8", colorNumero, Color.White, { escribirNumero("8") }, Modifier.weight(1f))
                BotonCalc("9", colorNumero, Color.White, { escribirNumero("9") }, Modifier.weight(1f))
                BotonCalc("*", colorOperador, Color.White, { elegirOperacion("*") }, Modifier.weight(1f))
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotonCalc("4", colorNumero, Color.White, { escribirNumero("4") }, Modifier.weight(1f))
                BotonCalc("5", colorNumero, Color.White, { escribirNumero("5") }, Modifier.weight(1f))
                BotonCalc("6", colorNumero, Color.White, { escribirNumero("6") }, Modifier.weight(1f))
                BotonCalc("-", colorOperador, Color.White, { elegirOperacion("-") }, Modifier.weight(1f))
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotonCalc("1", colorNumero, Color.White, { escribirNumero("1") }, Modifier.weight(1f))
                BotonCalc("2", colorNumero, Color.White, { escribirNumero("2") }, Modifier.weight(1f))
                BotonCalc("3", colorNumero, Color.White, { escribirNumero("3") }, Modifier.weight(1f))
                BotonCalc("+", colorOperador, Color.White, { elegirOperacion("+") }, Modifier.weight(1f))
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BotonCalc("0", colorNumero, Color.White, { escribirNumero("0") }, Modifier.weight(1f))
                BotonCalc(".", colorNumero, Color.White, {
                    if (!textoPantalla.contains(".")) escribirNumero(".")
                }, Modifier.weight(1f))
                BotonCalc("=", colorIgual, Color.White, { calcular() }, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BotonCalc(
    texto: String,
    colorFondo: Color,
    colorTexto: Color = Color.White,
    alClic: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = alClic,
        modifier = modifier.height(72.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo,
            contentColor = colorTexto
        )
    ) {
        Text(texto, fontSize = 26.sp, fontWeight = FontWeight.Medium)
    }
}