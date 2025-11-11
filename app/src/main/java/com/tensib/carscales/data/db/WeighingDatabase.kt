package com.tensib.carscales.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weighing::class], version = 1, exportSchema = false)
abstract class WeighingDatabase : RoomDatabase() {
    abstract fun weighingDao(): WeighingDao

    companion object {
        private var INSTANCE: WeighingDatabase? = null

        fun getInstance(context: Context): WeighingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeighingDatabase::class.java,
                    "weighing_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}