package com.bangkit.cunny.data.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("prediction_result")
	val predictionResult: PredictionResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class PredictionResult(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("predicted_class")
	val predictedClass: Int? = null,

	@field:SerializedName("predicted_label")
	val predictedLabel: String? = null
)
