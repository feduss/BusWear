package com.feduss.buswear.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feduss.buswear.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TimesViewModel(val lineId: String, val lineDirection: String, val stop: StopModel, private val db: AppDatabase): ViewModel() {

    var times = ArrayList<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getScheduledTimes()
        }
    }

    private suspend fun getScheduledTimes() = withContext(Dispatchers.IO) {

        times = ArrayList(
            db.linesDao().getScheduledTimes(
                lineId = lineId,
                stopId = stop.id
            )
        )
        _isLoading.value = false
    }
}