package com.feduss.buswear.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.feduss.buswear.enums.Params
import com.feduss.buswear.model.LinesViewModel
import com.feduss.buswear.model.StopModel
import com.feduss.buswear.model.TimesViewModel
import com.feduss.buswear.room.AppDatabase

@Suppress("UNCHECKED_CAST")
class TimesViewModelFactory(private val lineId: String, private val lineDirection: String, private val stop: StopModel, private val db: AppDatabase) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimesViewModel::class.java))
            return TimesViewModel(
                lineId = lineId,
                lineDirection = lineDirection,
                stop = stop,
                db = db
            ) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}