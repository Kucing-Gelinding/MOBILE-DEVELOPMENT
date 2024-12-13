package com.bangkit.cunny.ui.sub_materials

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.data.repository.MaterialsRepository

class SubMaterialViewModel : ViewModel() {
    val materials = MutableLiveData<List<SubMaterialModel>>()
}