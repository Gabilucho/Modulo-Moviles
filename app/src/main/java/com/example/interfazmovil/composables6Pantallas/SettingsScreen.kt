package com.example.myapplication.composable7

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interfazmovil.ui.theme.BackgroundApp
import com.example.interfazmovil.ui.theme.BackgroundComponent
import com.example.interfazmovil.ui.theme.ControlColor
import com.example.interfazmovil.ui.theme.PrimaryButton
import com.example.interfazmovil.ui.theme.SecondaryText
import kotlinx.coroutines.launch

@Preview (showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val settingsDataStore = remember(context) { SettingsDataStore(context.applicationContext) }

    // Cada estado sigue al Flow correspondiente: el switch refleja lo guardado.
    val notificationsEnabled by settingsDataStore.notificationsEnabled.collectAsState(true)
    val vibrationEnabled by settingsDataStore.vibrationEnabled.collectAsState(true)
    val soundsEnabled by settingsDataStore.soundsEnabled.collectAsState(true)
    val tipsEnabled by settingsDataStore.tipsEnabled.collectAsState(false)
    val username by settingsDataStore.username.collectAsState("")

    // Borrador editable. Al llegar el nombre guardado, el campo se sincroniza.
    var usernameText by remember(username) { mutableStateOf(username) }

    Scaffold(
        containerColor = BackgroundApp,
        topBar = {
            TopAppBar(
                title = { Text("Ajustes") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        // Este recurso drawable ya debe existir en tu proyecto.
                        Icon(
                            imageVector = Icons.Default.AcUnit,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = BackgroundApp,
                    titleContentColor = Color.Red,
                    navigationIconContentColor = Color.Blue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("CUENTA", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = SecondaryText)
            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BackgroundComponent)
            ) {
                UsernameSetting(
                    username = usernameText,
                    onUsernameChanged = { usernameText = it },
                    onSave = {
                        coroutineScope.launch {
                            settingsDataStore.setUsername(usernameText)
                        }
                    }
                )
            }

            Spacer(Modifier.height(32.dp))
            Text("PREFERENCIAS", fontSize = 14.sp, 
                fontWeight = FontWeight.Medium, 
                color = SecondaryText)
            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = BackgroundComponent)
            ) {
                Column {
                    SettingsSwitch(
                        title = "Notificaciones",
                        description = "Recibir avisos de la aplicación.",
                        enabled = notificationsEnabled,
                        onCheckedChange = { enabled ->
                            coroutineScope.launch { settingsDataStore.setNotificationsEnabled(enabled) }
                        }
                    )
                    SettingsSwitch(
                        title = "Vibración",
                        description = "Vibrar al recibir acciones.",
                        enabled = vibrationEnabled,
                        onCheckedChange = { enabled ->
                            coroutineScope.launch { settingsDataStore.setVibrationEnabled(enabled) }
                        }
                    )
                    SettingsSwitch(
                        title = "Sonidos",
                        description = "Reproducir sonidos en la aplicación.",
                        enabled = soundsEnabled,
                        onCheckedChange = { enabled ->
                            coroutineScope.launch { settingsDataStore.setSoundsEnabled(enabled) }
                        }
                    )
                    SettingsSwitch(
                        title = "Mostrar consejos",
                        description = "Ver consejos dentro de la aplicación.",
                        enabled = tipsEnabled,
                        onCheckedChange = { enabled ->
                            coroutineScope.launch { settingsDataStore.setTipsEnabled(enabled) }
                        }
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
            OutlinedButton(
                onClick = {
                    coroutineScope.launch { settingsDataStore.resetSettings() }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(2.dp, PrimaryButton)
            ) {
                Text(
                    "RESTABLECER AJUSTES",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryButton
                )
            }
        }
    }
}

@Composable
fun UsernameSetting(username: String,
                    onUsernameChanged: (String) -> Unit,
                    onSave: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChanged,
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = SecondaryText,
                unfocusedTextColor = SecondaryText,
                cursorColor = ControlColor,
                focusedLabelColor = ControlColor
            )
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryButton,
                contentColor = Color.White
            )
        ) {
            Text("Guardar", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SettingsSwitch(
    title: String,
    description: String,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 16.sp, 
                fontWeight = FontWeight.Medium,
                color = Color.White)
            Spacer(Modifier.height(4.dp))
            Text(description, fontSize = 14.sp, 
                fontWeight = FontWeight.Light, 
                color = SecondaryText)
        }
        Switch(
            checked = enabled,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                uncheckedThumbColor = SecondaryText,
               // checkedTrackColor = ControlColor,
                //uncheckedTrackColor = BackgroundComponentSelected,
                uncheckedBorderColor = SecondaryText
            )
        )
    }
}
