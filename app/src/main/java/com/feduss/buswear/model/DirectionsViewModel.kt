package com.feduss.buswear.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feduss.buswear.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DirectionsViewModel(val lineId: String, private val db: AppDatabase): ViewModel() {

    var directions = ArrayList<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getDirections()
        }
    }

    private suspend fun getDirections() = withContext(Dispatchers.IO) {

        directions = ArrayList(db.linesDao().getDirection(lineId = lineId))
        _isLoading.value = false
    }
}