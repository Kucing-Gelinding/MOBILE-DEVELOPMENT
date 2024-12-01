package com.bangkit.cunny.data.response

import com.google.gson.annotations.SerializedName

data class MaterialsResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("learningMaterials")
	val learningMaterials: LearningMaterials? = null
)

data class LearningMaterials(

	@field:SerializedName("sub_body_materials")
	val subBodyMaterials: List<String?>? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("sub_materials")
	val subMaterials: List<String?>? = null,

	@field:SerializedName("learning_image_path")
	val learningImagePath: String? = null
)
