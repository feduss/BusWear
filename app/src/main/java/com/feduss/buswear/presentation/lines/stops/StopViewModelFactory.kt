package com.feduss.buswear.presentation.lines.stops

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class StopsViewModelFactory(private val lineId: String, private val lineDirection: String,
                            private val db: SQLiteDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StopsViewModel::class.java))
            return StopsViewModel(
                lineId = lineId,
                lineDirection = lineDirection,
                db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}