package com.ejemplo.scaffold.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "03 · Navegación inferior", showBackground = true, showSystemUi = true, widthDp = 390, heightDp = 844)
@Composable
fun PantallaNavegacionInferior() {
    var seleccion by rememberSaveable { mutableIntStateOf(0) }
    val titulos = listOf("Inicio", "Favoritos", "Perfil")

    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text(titulos[seleccion]) }) },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = seleccion == 0,
                        onClick = { seleccion = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Inicio") }
                    )
                    NavigationBarItem(
                        selected = seleccion == 1,
                        onClick = { seleccion = 1 },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text("Favoritos") }
                    )
                    NavigationBarItem(
                        selected = seleccion == 2,
                        onClick = { seleccion = 2 },
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text("Perfil") }
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(titulos[seleccion], style = MaterialTheme.typography.headlineMedium)
                Text(when (seleccion) {
                    0 -> "Aquí puedes mostrar el resumen de la aplicación."
                    1 -> "Aquí aparecerían los elementos guardados."
                    else -> "Aquí puedes mostrar los datos del usuario."
                })
                Text("Pulsa las pestañas inferiores para cambiar este contenido.")
            }
        }
    }
}
