package com.bangkit.cunny.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable