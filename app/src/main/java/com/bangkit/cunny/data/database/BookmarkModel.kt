package com.bangkit.cunny.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.utils.Converters // Ensure the proper import
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@TypeConverters(Converters::class)  // Ensure TypeConverters is specified here as well
data class BookmarkModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "learning_image_path")
    var learningImagePath: String = "",

    @ColumnInfo(name = "sub_materials")
    var subMaterials: List<SubMaterialModel>
) : Parcelable
