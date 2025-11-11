package com.tensib.carscales.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeighingDao {
    @Insert
    suspend fun insertWeighing(weighing: Weighing)

    @Query("SELECT * FROM weighings ORDER BY timestamp DESC")
    fun getAllWeighings(): LiveData<List<Weighing>>

    @Query("SELECT * FROM weighings WHERE DATE(timestamp/1000, 'unixepoch') = DATE(:date/1000, 'unixepoch') ORDER BY timestamp DESC")
    fun getWeighingsByDate(date: Long): LiveData<List<Weighing>>

    @Query("""
        SELECT * FROM weighings 
        WHERE strftime('%Y-%W', timestamp/1000, 'unixepoch') = strftime('%Y-%W', :date/1000, 'unixepoch')
        ORDER BY timestamp DESC
    """)
    fun getWeighingsByWeek(date: Long): LiveData<List<Weighing>>

    @Query("""
        SELECT * FROM weighings 
        WHERE strftime('%Y-%m', timestamp/1000, 'unixepoch') = strftime('%Y-%m', :date/1000, 'unixepoch')
        ORDER BY timestamp DESC
    """)
    fun getWeighingsByMonth(date: Long): LiveData<List<Weighing>>

    @Query("""
        SELECT * FROM weighings 
        WHERE driver_name LIKE '%' || :searchText || '%'
        OR vehicle_number LIKE '%' || :searchText || '%'
        OR cargo_description LIKE '%' || :searchText || '%'
        ORDER BY timestamp DESC
    """)
    fun searchWeighings(searchText: String): LiveData<List<Weighing>>

    @Query("DELETE FROM weighings")
    suspend fun deleteAllWeighings()
}