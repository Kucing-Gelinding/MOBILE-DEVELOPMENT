package com.bangkit.cunny.data.repository

import com.bangkit.cunny.data.response.MaterialsResponse
import com.bangkit.cunny.data.retrofit.ApiService
import retrofit2.Response


class MaterialsRepository (private val apiService: ApiService) {

    // Fungsi untuk mengambil data materi dari API
    suspend fun getMaterials(): MaterialsResponse {
        return apiService.getMaterials()
    }

    companion object {
        @Volatile
        private var instance: MaterialsRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): MaterialsRepository =
            instance ?: synchronized(this) {
                instance ?: MaterialsRepository(apiService)
            }.also { instance = it }
    }
}