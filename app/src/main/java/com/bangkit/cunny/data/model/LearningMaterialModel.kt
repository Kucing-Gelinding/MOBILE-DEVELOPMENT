package com.bangkit.cunny.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LearningMaterialModel(
	val id: Int = 0,
	val title: String,
	val description: String,
	val subMaterial: List<SubMaterialModel>,
	val learningImagePath: String

):Parcelable

