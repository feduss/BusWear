package com.feduss.buswear.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feduss.buswear.model.StopsViewModel
import com.feduss.buswear.room.AppDatabase

@Suppress("UNCHECKED_CAST")
class StopsViewModelFactory(private val lineId: String, private val lineDirection: String,
                            private val db: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StopsViewModel::class.java))
            return StopsViewModel(
                lineId = lineId,
                lineDirection = lineDirection,
                db = db) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}