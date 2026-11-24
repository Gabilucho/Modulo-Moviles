package com.ejemplo.scaffold.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "05 · Menú lateral", showBackground = true, showSystemUi = true, widthDp = 390, heightDp = 844)
@Composable
fun PantallaMenuLateral() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var seleccion by rememberSaveable { mutableStateOf("Inicio") }

    MaterialTheme {
        // El menú lateral envuelve al Scaffold.
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("Mi aplicación", modifier = Modifier.padding(24.dp),
                        style = MaterialTheme.typography.titleLarge)
                    NavigationDrawerItem(
                        label = { Text("Inicio") },
                        selected = seleccion == "Inicio",
                        onClick = {
                            seleccion = "Inicio"
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    NavigationDrawerItem(
                        label = { Text("Ajustes") },
                        selected = seleccion == "Ajustes",
                        onClick = {
                            seleccion = "Ajustes"
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(seleccion) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu,
                                    contentDescription = "Abrir menú lateral")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(seleccion, style = MaterialTheme.typography.headlineMedium)
                    Text("Pulsa el icono de menú para desplegar las opciones laterales.")
                    Text("El menú cambia la selección y se cierra al elegir una opción.")
                }
            }
        }
    }
}
