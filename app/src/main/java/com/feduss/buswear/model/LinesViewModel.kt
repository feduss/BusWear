package com.feduss.buswear.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feduss.buswear.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LinesViewModel(private val db: AppDatabase): ViewModel() {

    var lines = mutableListOf<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()
    init {
        viewModelScope.launch {
            getLines()
        }
    }

    private suspend fun getLines() = withContext(Dispatchers.IO) {

        lines = ArrayList(db.linesDao().getLines())
        _isLoading.value = false
    }
}