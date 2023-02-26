package com.feduss.buswear.presentation.lines.stops

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StopsViewModel(val lineId: String, val lineDirection: String,
                     private val db: SQLiteDatabase): ViewModel() {

    var stops = ArrayList<String>()
    private var _isLoading = MutableStateFlow(true)
    var isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            //getStops()
        }
    }

    private suspend fun getStops() = withContext(Dispatchers.IO) {
        val tempList = ArrayList<String>()

        val lineStopsColumnKey = "stops"

        val query = "SELECT DISTINCT s.stop_name as $lineStopsColumnKey FROM Stops s " +
                "JOIN Stop_times st ON s.stop_id = st.stop_id " +
                "JOIN Trips t ON st.trip_id = t.trip_id " +
                "WHERE t.route_id = '$lineId' " +
                "AND t.trip_headsign LIKE '%$lineDirection%' " +
                "ORDER BY st.stop_sequence"

        Log.e("BUSWEAR:", "query: $query")

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val lineStopsColumnIndex = cursor.getColumnIndex(lineStopsColumnKey)

                if(lineStopsColumnIndex >= 0){
                    val stop = cursor.getString(lineStopsColumnIndex)

                    tempList.add(stop)
                }

                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        stops = tempList
        _isLoading.value = false
    }
}