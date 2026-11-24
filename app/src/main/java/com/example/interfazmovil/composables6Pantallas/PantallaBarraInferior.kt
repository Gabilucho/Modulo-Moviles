package com.ejemplo.scaffold.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
@Preview(name = "04 · Barra de acciones inferior", showBackground = true, showSystemUi = true, widthDp = 390, heightDp = 844)
@Composable
fun PantallaBarraInferior() {
    var mensaje by rememberSaveable()
    { mutableStateOf("Prueba las acciones de la barra inferior.") }

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Mis notas") }) },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(
                            onClick = { mensaje = "Búsqueda seleccionada." }) {
                            Icon(Icons.Default.Search,
                                contentDescription = "Buscar notas")
                        }
                        IconButton(
                            onClick = { mensaje = "Favoritos seleccionados." }) {
                            Icon(Icons.Default.Favorite,
                                contentDescription = "Ver favoritos")
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { mensaje = "Nueva nota creada." }) {
                            Icon(Icons.Default.Add,
                                contentDescription = "Crear nota")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Acciones a mano", style = MaterialTheme.typography.headlineSmall)
                Text("BottomAppBar agrupa acciones y un botón flotante en la parte inferior.")
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(mensaje,
                        modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}
