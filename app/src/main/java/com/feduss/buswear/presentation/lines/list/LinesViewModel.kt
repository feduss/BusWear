package com.feduss.buswear.presentation.lines.list

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel

class LinesViewModel: ViewModel() {

    var lines = ArrayList<String>()
    fun setLines(db: SQLiteDatabase) {
        val tempList = ArrayList<String>()

        val lineNameColumnKey = "line_name"

        val query = "SELECT r.route_id as $lineNameColumnKey " +
                "FROM Routes r " +
                "ORDER BY r.route_id"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val lineNameColumnIndex = cursor.getColumnIndex(lineNameColumnKey)

                if(lineNameColumnIndex >= 0){
                    val id = cursor.getString(lineNameColumnIndex)

                    tempList.add(id)
                }

                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()

        lines = tempList
    }
}