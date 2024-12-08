package com.bangkit.cunny.utils

import androidx.room.TypeConverter
import com.bangkit.cunny.data.model.SubMaterialModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromSubMaterialList(value: List<SubMaterialModel>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSubMaterialList(value: String): List<SubMaterialModel> {
        val gson = Gson()
        val listType = object : TypeToken<List<SubMaterialModel>>() {}.type
        return gson.fromJson(value, listType)
    }
}