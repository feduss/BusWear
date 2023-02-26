package com.feduss.buswear.presentation.lines.list

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LinesViewModel(private val db: SQLiteDatabase): ViewModel() {

    var lines = mutableListOf<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()
    init {
        viewModelScope.launch {
            getLines()
        }
    }

    private suspend fun getLines() = withContext(Dispatchers.IO) {
        val tempList = ArrayList<String>()

        val lineNameColumnKey = "line_name"

        val query = "SELECT r.route_id as $lineNameColumnKey " +
                "FROM Routes r " +
                "ORDER BY r.route_id"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val lineNameColumnIndex = cursor.getColumnIndex(lineNameColumnKey)

                if (lineNameColumnIndex >= 0) {
                    val id = cursor.getString(lineNameColumnIndex)

                    tempList.add(id)
                }

                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        lines = tempList
        _isLoading.value = false
    }
}