package com.tensib.carscales.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tensib.carscales.viewmodel.CarScalesViewModel

@Composable
fun CarScalesApp(viewModel: CarScalesViewModel) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    label = { Text("Основная") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("История") },
                    icon = { Icon(Icons.Default.List, contentDescription = "History") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    label = { Text("Настройки") },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    label = { Text("Информация") },
                    icon = { Icon(Icons.Default.Info, contentDescription = "Info") }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        when (selectedTab) {
            0 -> MainWeightScreen(viewModel, Modifier.padding(innerPadding))
            1 -> HistoryScreen(viewModel, Modifier.padding(innerPadding))
            2 -> SettingsScreen(viewModel, Modifier.padding(innerPadding))
            3 -> InfoScreen(Modifier.padding(innerPadding))
        }
    }
}