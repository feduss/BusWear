package com.feduss.buswear.presentation.lines.list

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LinesViewModelFactory(private val db: SQLiteDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LinesViewModel::class.java))
            return LinesViewModel(db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}