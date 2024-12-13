package com.bangkit.cunny.data.retrofit

import com.bangkit.cunny.data.response.MaterialsResponse
import com.bangkit.cunny.data.response.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("learning-materials")
    suspend fun getMaterials(
    ): MaterialsResponse

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part uploaded_file: MultipartBody.Part
    ): PredictionResponse
}