package org.example.project
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
enum class Pantalla { EJ1, EJ2, EJ3, EJ4, EJ5, EJ6 }


@Composable
@Preview
fun App() {
    var pantalla by remember { mutableStateOf<Pantalla?>(null) }


    if (pantalla == null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Menú de Ejercicios", style = MaterialTheme.typography.h5)
            Pantalla.values().forEach {
                Button(onClick = { pantalla = it }, modifier = Modifier.padding(4.dp)) {
                    Text(it.name)
                }
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { pantalla = null }) { Text("Volver al menú") }
            Spacer(Modifier.height(16.dp))
            when (pantalla) {
                Pantalla.EJ1 -> Ejercicio1()
                Pantalla.EJ2 -> Ejercicio2()
                Pantalla.EJ3 -> Ejercicio3()
                Pantalla.EJ4 -> Ejercicio4()
                Pantalla.EJ5 -> Ejercicio5()
                Pantalla.EJ6 -> Ejercicio6()
                null -> {}
            }
        }
    }
}


@Composable
fun Ejercicio1() {
    var textoBoton by remember { mutableStateOf("Pulsa el botón") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Al hacer clic, cambiamos el texto del botón")

        Button(onClick = { textoBoton = "¡Botón pulsado!" }) {
            Text(textoBoton)
        }
    }
}

@Composable
fun Ejercicio2() {
    var mostrarTexto by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // espacio vertical entre elementos
    ) {
        Text("Podemos visualizar o no un texto")

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp) // espacio entre botones
        ) {
            Button(onClick = { mostrarTexto = true }) {
                Text("Mostrar")
            }
            Button(onClick = { mostrarTexto = false }) {
                Text("Ocultar")
            }
        }
    }
    if (mostrarTexto) {
        Text("texto visible")
    }
}


@Composable
fun Ejercicio3() {
    var pantallaActual by remember { mutableStateOf("ej3") }
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Experimentamos creando una especie de submenú basado en botones")
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (pantallaActual == "ej3") {
                Button(onClick = { pantallaActual = "Submenu1" }) {
                    Text("Submenú 1")
                }
                Button(onClick = { pantallaActual = "Submenu2" }) {
                    Text("Submenú 2")
                }
            }

            if (pantallaActual == "Submenu1") {
                Text("Pantalla opcíon 1")
                Button(onClick = { pantallaActual = "ej3" }) {
                    Text("Volver")
                }
            }

            if (pantallaActual == "Submenu2") {
                Text("Pantalla opcíon 1")
                Button(onClick = { pantallaActual = "ej3" }) {
                    Text("Volver")
                }
            }
        }
    }
}


@Composable
fun Ejercicio4() {
    var contador by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Típico ejemplo de incremento de contador")
        Text("Contador: ${contador}")
        Row {
            Button(onClick = {
                contador++
                println(contador)
            }) {
                Text("Incrementar")
            }
            Button(onClick = {
                contador=0
            }) {
                Text("Reiniciar")
            }
        }
    }
}


@Composable
fun Ejercicio5() {
    var nombreSaludo by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Practicamos con el TextField")
        Text("Introduce tu nombre")
        TextField(
        value = nombreSaludo,
        onValueChange = { nombreSaludo = it },
        label = { Text("") }
    )
        if (nombreSaludo.isNotEmpty()) {
            Text("¡Hola, $nombreSaludo!")
        }
    }
}




@Composable
fun Ejercicio6() {
    Text("Dibujamos un tablero de 3x3...")

    var tablero by remember {
        mutableStateOf(List(3) { List(3) { "" } })
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tablero.forEachIndexed { filaIndex, fila ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                fila.forEachIndexed { colIndex, celda ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .border(1.dp, Color.Black)
                            .clickable {
                                if (tablero[filaIndex][colIndex].isEmpty()) {

                                    val newTablero = tablero
                                        .map { it.toMutableList() }
                                        .toMutableList()

                                    newTablero[filaIndex][colIndex] = "\u2714"

                                    tablero = newTablero.map { it.toList() }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(celda)
                    }
                }
            }
        }
    }
}



fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Ejercicios Compose Desktop") {
        App()
    }
}