package com.tensib.carscales.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    text = "Весы Автомобильные",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Версия 1.0",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Разработчик",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "ООО Тенсиб",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Text(
                    text = "Город: Красноярск",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Text(
                    text = "Россия",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                Text(
                    text = "Контактные данные",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "Email: info@tensib.ru",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Text(
                    text = "Телефон: +7 (391) XXX-XX-XX",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                Text(
                    text = "Веб-сайт: www.tensib.ru",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "© 2025 ООО Тенсиб. Все права защищены.",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}