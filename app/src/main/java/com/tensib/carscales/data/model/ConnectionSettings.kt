package com.tensib.carscales.data.model

data class ConnectionSettings(
    val serverIp: String = "127.0.0.1",
    val serverPort: Int = 9000,
    val isConnected: Boolean = false,
    val lastError: String = ""
)