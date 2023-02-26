package com.feduss.buswear.presentation.lines.directions

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DirectionsViewModel(val lineId: String, private val db: SQLiteDatabase): ViewModel() {

    var directions = ArrayList<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getDirections()
        }
    }

    private suspend fun getDirections() = withContext(Dispatchers.IO) {
        val tempList = ArrayList<String>()

        val lineDirectionsColumnKey = "line_directions"

        val query = "SELECT t.trip_headsign as $lineDirectionsColumnKey " +
                "FROM Trips t " +
                "WHERE t.route_id = '$lineId' " +
                "GROUP BY t.trip_headsign"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val lineDirectionColumnIndex = cursor.getColumnIndex(lineDirectionsColumnKey)

                if(lineDirectionColumnIndex >= 0){
                    val direction = cursor.getString(lineDirectionColumnIndex)

                    tempList.add(direction)
                }

                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        directions = tempList
        _isLoading.value = false
    }
}