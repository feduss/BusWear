package com.feduss.buswear.presentation.lines.directions

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel

class DirectionsViewModel(val lineId: String): ViewModel() {

    var directions = ArrayList<String>()

    fun setDirections(db: SQLiteDatabase) {
        val tempList = ArrayList<String>()

        val lineDirectionsColumnKey = "line_directions"

        val query = "SELECT t.trip_headsign as $lineDirectionsColumnKey " +
                "FROM Trips t " +
                "WHERE t.route_id = \"$lineId\" " +
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
    }
}