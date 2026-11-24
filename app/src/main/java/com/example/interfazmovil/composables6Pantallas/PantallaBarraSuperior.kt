package com.ejemplo.scaffold.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "01 · Barra superior", showBackground = true, showSystemUi = true, widthDp = 390, heightDp = 844)
@Composable
fun PantallaBarraSuperior() {
    var mensaje by rememberSaveable { mutableStateOf("Pulsa el botón o el icono de información.") }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Menú principal") },
                    actions = {
                        IconButton(
                            onClick = { mensaje = "Has pulsado el icono de información." }
                        ) {
                            Icon(Icons.Default.Info, contentDescription = "Información")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Barra superior", style = MaterialTheme.typography.headlineSmall)
                Text("TopAppBar coloca el título y las acciones en la parte superior.")
                Button(onClick = { mensaje = "Has pulsado el botón principal." }) {
                    Text("Pulsar botón")
                }
                Text(mensaje)
            }
        }
    }
}
