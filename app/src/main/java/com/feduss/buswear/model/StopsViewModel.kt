package com.feduss.buswear.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feduss.buswear.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StopsViewModel(
    val lineId: String, val lineDirection: String,
    private val db: AppDatabase
): ViewModel() {

    var stops = ArrayList<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getStops()
        }
    }

    private suspend fun getStops() = withContext(Dispatchers.IO) {

        stops = ArrayList(
            db.linesDao().getStops(
                lineId = lineId,
                lineDirection = lineDirection
            )
        )
        _isLoading.value = false
    }
}