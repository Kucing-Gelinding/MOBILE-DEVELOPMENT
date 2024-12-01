package com.bangkit.cunny.data.retrofit

import com.bangkit.cunny.data.response.MaterialsResponse
import com.bangkit.cunny.data.response.SubMaterialsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("learning-materials")
    suspend fun getMaterials(
    ): MaterialsResponse

    @GET("materials/id")
    suspend fun getSubMaterials(
    ): SubMaterialsResponse
}