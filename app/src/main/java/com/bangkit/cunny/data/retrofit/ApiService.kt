package com.bangkit.cunny.data.retrofit

import com.bangkit.cunny.data.response.MaterialsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("learning-materials")
    suspend fun getMaterials(
    ): MaterialsResponse

}