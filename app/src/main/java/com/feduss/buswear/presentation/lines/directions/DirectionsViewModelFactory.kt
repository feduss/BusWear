package com.feduss.buswear.presentation.lines.directions

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DirectionsViewModelFactory(private val lineId: String, private val db: SQLiteDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectionsViewModel::class.java))
            return DirectionsViewModel(
                lineId = lineId,
                db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}