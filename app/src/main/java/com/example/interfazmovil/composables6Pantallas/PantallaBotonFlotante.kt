package com.ejemplo.scaffold.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "02 · Botón flotante", showBackground = true, showSystemUi = true, widthDp = 390, heightDp = 844)
@Composable
fun PantallaBotonFlotante() {
    var tareas by rememberSaveable { mutableIntStateOf(3) }

    MaterialTheme {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text("Mis tareas") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = { tareas++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir tarea")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Tienes $tareas tareas", style = MaterialTheme.typography.headlineSmall)
                Text("El botón + ocupa el espacio floatingActionButton del Scaffold.")
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text("Pulsa + para aumentar el contador.", modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}
