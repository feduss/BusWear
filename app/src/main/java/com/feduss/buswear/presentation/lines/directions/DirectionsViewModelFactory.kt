package com.feduss.buswear.presentation.lines.directions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DirectionsViewModelFactory(private val lineId: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectionsViewModel::class.java))
            return DirectionsViewModel(lineId) as T
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}