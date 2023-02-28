package com.feduss.buswear.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feduss.buswear.model.DirectionsViewModel
import com.feduss.buswear.room.AppDatabase

@Suppress("UNCHECKED_CAST")
class DirectionsViewModelFactory(private val lineId: String, private val db: AppDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectionsViewModel::class.java))
            return DirectionsViewModel(
                lineId = lineId,
                db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}