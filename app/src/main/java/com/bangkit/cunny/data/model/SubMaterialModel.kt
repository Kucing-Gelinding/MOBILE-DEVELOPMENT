package com.bangkit.cunny.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubMaterialModel (
    val id: Int? = null,
    val subMaterial: String? = null,
    val subBodyMaterial : String? = null,
    val learningImagePath: String
):Parcelable