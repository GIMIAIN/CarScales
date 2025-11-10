package com.tensib.carscales.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weighings")
data class Weighing(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "weight")
    val weight: Float = 0f,

    @ColumnInfo(name = "driver_name")
    val driverName: String = "",

    @ColumnInfo(name = "vehicle_number")
    val vehicleNumber: String = "",

    @ColumnInfo(name = "cargo_description")
    val cargoDescription: String = "",

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "date_formatted")
    val dateFormatted: String = ""
)