package com.feduss.buswear.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feduss.buswear.model.LinesViewModel
import com.feduss.buswear.room.AppDatabase

@Suppress("UNCHECKED_CAST")
class LinesViewModelFactory(private val db: AppDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LinesViewModel::class.java))
            return LinesViewModel(db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}