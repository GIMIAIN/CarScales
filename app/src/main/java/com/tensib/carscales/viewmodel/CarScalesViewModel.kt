package com.tensib.carscales.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tensib.carscales.data.db.Weighing
import com.tensib.carscales.data.db.WeighingDatabase
import com.tensib.carscales.data.model.ConnectionSettings
import com.tensib.carscales.data.repository.WeighingRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CarScalesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WeighingRepository
    private val _currentWeight = MutableLiveData<Float>(0f)
    val currentWeight: LiveData<Float> = _currentWeight

    private val _connectionSettings = MutableLiveData<ConnectionSettings>(
        ConnectionSettings()
    )
    val connectionSettings: LiveData<ConnectionSettings> = _connectionSettings

    private val _allWeighings = MutableLiveData<List<Weighing>>(emptyList())
    val allWeighings: LiveData<List<Weighing>> = _allWeighings

    private val _selectedFilterType = MutableLiveData<String>("day")
    val selectedFilterType: LiveData<String> = _selectedFilterType

    init {
        val database = WeighingDatabase.getInstance(application)
        val dao = database.weighingDao()
        repository = WeighingRepository(dao)
    }

    fun updateCurrentWeight(weight: Float) {
        _currentWeight.value = weight
    }

    fun saveWeighing(
        weight: Float,
        driverName: String,
        vehicleNumber: String,
        cargoDescription: String
    ) {
        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val weighing = Weighing(
                weight = weight,
                driverName = driverName,
                vehicleNumber = vehicleNumber,
                cargoDescription = cargoDescription,
                timestamp = System.currentTimeMillis(),
                dateFormatted = dateFormat.format(Date())
            )
            repository.insertWeighing(weighing)
        }
    }

    fun loadWeighingsByFilter() {
        val filterType = _selectedFilterType.value ?: "day"
        val now = System.currentTimeMillis()

        val liveData = when (filterType) {
            "week" -> repository.getWeighingsByWeek(now)
            "month" -> repository.getWeighingsByMonth(now)
            else -> repository.getWeighingsByDate(now)
        }

        liveData.observeForever { weighings ->
            _allWeighings.value = weighings
        }
    }

    fun searchWeighings(searchText: String) {
        if (searchText.isEmpty()) {
            loadWeighingsByFilter()
        } else {
            repository.searchWeighings(searchText).observeForever { weighings ->
                _allWeighings.value = weighings
            }
        }
    }

    fun setFilterType(filterType: String) {
        _selectedFilterType.value = filterType
        loadWeighingsByFilter()
    }

    fun updateConnectionSettings(ip: String, port: Int) {
        _connectionSettings.value = _connectionSettings.value?.copy(
            serverIp = ip,
            serverPort = port
        ) ?: ConnectionSettings(serverIp = ip, serverPort = port)
    }
}