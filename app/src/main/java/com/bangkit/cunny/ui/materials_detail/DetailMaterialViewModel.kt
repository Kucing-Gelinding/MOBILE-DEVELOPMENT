package com.bangkit.cunny.ui.materials_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.cunny.data.repository.MaterialsRepository
import com.bangkit.cunny.data.response.LearningMaterial
import com.bangkit.cunny.data.response.MaterialsResponse
import com.bangkit.cunny.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class DetailMaterialViewModel (private val repository: MaterialsRepository) : ViewModel() {

    private val _materials = MutableLiveData<MaterialsResponse>()
    val materials: LiveData<MaterialsResponse> get() = _materials

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _selectedMaterial = MutableLiveData<LearningMaterial?>()
    val selectedMaterial: LiveData<LearningMaterial?> get() = _selectedMaterial

    fun fetchMaterialById(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getMaterials()
                val material = response.learningMaterials?.find { it.id == id }
                if (material != null) {
                    _selectedMaterial.value = material
                } else {
                    _error.value = "Material not found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}