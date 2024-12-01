package com.bangkit.cunny.ui.materials

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.cunny.data.repository.MaterialsRepository
import com.bangkit.cunny.data.response.MaterialsResponse
import kotlinx.coroutines.launch

class MaterialViewModel(private val repository: MaterialsRepository) : ViewModel() {

    private val _materials = MutableLiveData<MaterialsResponse>()
    val materials: LiveData<MaterialsResponse> get() = _materials

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchMaterials() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getMaterials()
                if (response != null) {
                    _materials.value = response
                } else {
                    _error.value = "Materials not found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
