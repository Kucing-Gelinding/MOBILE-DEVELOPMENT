package com.bangkit.cunny.di

import android.content.Context
import com.bangkit.cunny.data.repository.MaterialsRepository
import com.bangkit.cunny.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): MaterialsRepository {
        val apiService = ApiConfig.getApiService()
        return MaterialsRepository.getInstance(apiService)
    }
}