package com.tensib.carscales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensib.carscales.viewmodel.CarScalesViewModel
import kotlinx.coroutines.delay

@Composable
fun MainWeightScreen(viewModel: CarScalesViewModel, modifier: Modifier = Modifier) {
    val currentWeight by viewModel.currentWeight.observeAsState(0f)
    var driverName by remember { mutableStateOf("") }
    var vehicleNumber by remember { mutableStateOf("") }
    var cargoDescription by remember { mutableStateOf("") }
    var showSaveDialog by remember { mutableStateOf(false) }
    var saveSuccess by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Weight Display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Текущий вес",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = String.format("%.1f", currentWeight),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "кг",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Fields
        OutlinedTextField(
            value = driverName,
            onValueChange = { driverName = it },
            label = { Text("ФИО водителя") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = vehicleNumber,
            onValueChange = { vehicleNumber = it },
            label = { Text("Номер автомобиля") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = cargoDescription,
            onValueChange = { cargoDescription = it },
            label = { Text("Описание груза") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            minLines = 2
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = { showSaveDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Сохранить взвешивание")
        }

        // Test Weight Update Button
        Button(
            onClick = { viewModel.updateCurrentWeight((Math.random() * 50000).toFloat()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Симулировать вес (тест)")
        }

        if (saveSuccess) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(
                    text = "✓ Взвешивание сохранено!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            LaunchedEffect(Unit) {
                delay(2000)
                saveSuccess = false
                driverName = ""
                vehicleNumber = ""
                cargoDescription = ""
            }
        }
    }

    if (showSaveDialog) {
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Сохранить взвешивание?") },
            text = {
                Column {
                    Text("Вес: ${String.format("%.1f", currentWeight)} кг")
                    Text("Водитель: $driverName")
                    Text("Машина: $vehicleNumber")
                    Text("Груз: $cargoDescription")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.saveWeighing(
                            currentWeight,
                            driverName,
                            vehicleNumber,
                            cargoDescription
                        )
                        showSaveDialog = false
                        saveSuccess = true
                    }
                ) {
                    Text("Да")
                }
            },
            dismissButton = {
                Button(onClick = { showSaveDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}