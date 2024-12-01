package com.bangkit.cunny.ui.materials

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.cunny.data.repository.MaterialsRepository

class MaterialViewModelFactory(private val repository: MaterialsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaterialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MaterialViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}