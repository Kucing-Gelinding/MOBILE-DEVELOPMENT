package com.bangkit.cunny.data.response

import com.google.gson.annotations.SerializedName

data class MaterialsResponse(

	@field:SerializedName("learningMaterials")
	val learningMaterials: List<LearningMaterial>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LearningMaterial(

	@field:SerializedName("sub_body_materials")
	val subBodyMaterials: List<List<String?>?>? = null,

	@field:SerializedName("description")
	val description: String = "",

	@field:SerializedName("id")
	val id: Int = 0,

	@field:SerializedName("title")
	val title: String = "",

	@field:SerializedName("sub_materials")
	val subMaterials: List<List<String?>?>? = null,

	@field:SerializedName("learning_image_path")
	val learningImagePath: String = ""
)

