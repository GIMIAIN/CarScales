package com.tensib.carscales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensib.carscales.data.model.ConnectionSettings
import com.tensib.carscales.viewmodel.CarScalesViewModel


@Composable
fun SettingsScreen(viewModel: CarScalesViewModel, modifier: Modifier = Modifier) {
    val settings by viewModel.connectionSettings.observeAsState(ConnectionSettings())
    var ipAddress by remember { mutableStateOf(settings.serverIp) }
    var port by remember { mutableStateOf(settings.serverPort.toString()) }
    var connectionStatus by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Параметры подключения TCP",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TCP Server Settings
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = ipAddress,
                    onValueChange = { ipAddress = it },
                    label = { Text("IP адрес сервера") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = port,
                    onValueChange = { port = it },
                    label = { Text("Порт") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    onClick = {
                        viewModel.updateConnectionSettings(ipAddress, port.toIntOrNull() ?: 9000)
                        connectionStatus = "✓ Параметры сохранены: $ipAddress:$port"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .height(48.dp)
                ) {
                    Text("Сохранить параметры")
                }
            }
        }

        if (connectionStatus.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = connectionStatus,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Text(
            text = "Данные терминала CAS CI200A",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Протокол: RS-485", fontSize = 14.sp)
                Text("Скорость передачи: 9600 бод", fontSize = 14.sp)
                Text("Формат данных: ASCII", fontSize = 14.sp)
                Text("Периодичность: Непрерывная", fontSize = 14.sp)
            }
        }
    }
}