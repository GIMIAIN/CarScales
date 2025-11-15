package com.tensib.carscales.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import com.tensib.carscales.ui.components.FilterButton
import com.tensib.carscales.ui.components.WeighingCard
import com.tensib.carscales.viewmodel.CarScalesViewModel
import kotlin.collections.emptyList

@Composable
fun HistoryScreen(viewModel: CarScalesViewModel, modifier: Modifier = Modifier) {
    val weighings by viewModel.allWeighings.observeAsState(emptyList())
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("day") }

    LaunchedEffect(Unit) {
        viewModel.loadWeighingsByFilter()
    }

    Column(modifier = modifier.fillMaxSize()) {
        // Filter Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("day" to "День", "week" to "Неделя", "month" to "Месяц").forEach { (value, label) ->
                FilterButton(
                    label = label,
                    isSelected = selectedFilter == value,
                    onClick = {
                        selectedFilter = value
                        viewModel.setFilterType(value)
                    }
                )
            }
        }

        // Search Field
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchWeighings(it)
            },
            label = { Text("Поиск (водитель, машина, груз)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Weighings List
        if (weighings.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет данных взвешивания", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(weighings) { weighing ->
                    WeighingCard(weighing)
                }
            }
        }
    }
}