package com.bangkit.cunny.data.database

import androidx.room.TypeConverter
import com.bangkit.cunny.data.model.SubMaterialModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    // Mengonversi List<SubMaterialModel> menjadi String (JSON)
    @TypeConverter
    fun fromSubMaterialList(subMaterials: List<SubMaterialModel>): String {
        val gson = Gson()
        return gson.toJson(subMaterials)
    }

    // Mengonversi String (JSON) kembali menjadi List<SubMaterialModel>
    @TypeConverter
    fun toSubMaterialList(data: String): List<SubMaterialModel> {
        val gson = Gson()
        val listType = object : TypeToken<List<SubMaterialModel>>() {}.type
        return gson.fromJson(data, listType)
    }
}