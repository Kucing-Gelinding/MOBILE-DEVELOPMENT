package com.bangkit.cunny.helper

import android.content.Context
import com.bangkit.cunny.data.model.SubMaterialModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesHelper(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("RecentMaterialsPrefs", Context.MODE_PRIVATE)

    fun saveRecentMaterial(material: SubMaterialModel) {
        val recentMaterials = getRecentMaterials().toMutableList()

        // Hindari duplikasi berdasarkan ID atau properti unik lainnya
        if (!recentMaterials.any { it.subMaterial == material.subMaterial }) {
            recentMaterials.add(material)
        }

        if (recentMaterials.size >= 10) {
            recentMaterials.removeAt(0) // Hapus item pertama (yang paling lama)
        }

        // Simpan kembali list recent materials ke SharedPreferences
        val materialsJson = Gson().toJson(recentMaterials)
        sharedPreferences.edit().putString("recent_materials", materialsJson).apply()
    }

    fun getRecentMaterials(): List<SubMaterialModel> {
        val materialsJson = sharedPreferences.getString("recent_materials", null)
        return if (materialsJson.isNullOrEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<SubMaterialModel>>() {}.type
            Gson().fromJson(materialsJson, type)
        }
    }
}
