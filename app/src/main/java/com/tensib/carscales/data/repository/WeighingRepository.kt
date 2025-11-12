package com.tensib.carscales.data.repository

import androidx.lifecycle.LiveData
import com.tensib.carscales.data.db.Weighing
import com.tensib.carscales.data.db.WeighingDao

class WeighingRepository(private val weighingDao: WeighingDao) {
    suspend fun insertWeighing(weighing: Weighing) {
        weighingDao.insertWeighing(weighing)
    }

    fun getAllWeighings(): LiveData<List<Weighing>> = weighingDao.getAllWeighings()

    fun getWeighingsByDate(date: Long): LiveData<List<Weighing>> = weighingDao.getWeighingsByDate(date)

    fun getWeighingsByWeek(date: Long): LiveData<List<Weighing>> = weighingDao.getWeighingsByWeek(date)

    fun getWeighingsByMonth(date: Long): LiveData<List<Weighing>> = weighingDao.getWeighingsByMonth(date)

    fun searchWeighings(searchText: String): LiveData<List<Weighing>> = weighingDao.searchWeighings(searchText)
}