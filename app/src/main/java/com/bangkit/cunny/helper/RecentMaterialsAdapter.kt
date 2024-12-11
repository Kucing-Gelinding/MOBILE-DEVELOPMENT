package com.bangkit.cunny.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.data.model.SubMaterialModel
import com.bangkit.cunny.databinding.RecentListBinding
import com.bumptech.glide.Glide

class RecentMaterialsAdapter : RecyclerView.Adapter<RecentMaterialsAdapter.ViewHolder>() {

    private val materials = mutableListOf<SubMaterialModel>()
    private var onItemClickCallback: ((SubMaterialModel) -> Unit)? = null

    fun setMaterials(data: List<SubMaterialModel>) {
        materials.clear()
        if (data.isEmpty()) {
            // Tambahkan placeholder jika data kosong
            materials.add(
                SubMaterialModel(
                    subMaterial = "Kamu belum membaca material apapun",
                    learningImagePath = "android.resource://com.bangkit.cunny/drawable/empty" // Path ke drawable
                )
            )
        } else {
            materials.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(callback: (SubMaterialModel) -> Unit) {
        onItemClickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val material = materials[position]
        holder.bind(material)
        if (material.subMaterial != "Kamu belum membaca material apapun") {
            holder.itemView.setOnClickListener { onItemClickCallback?.invoke(material) }
        } else {
            holder.itemView.setOnClickListener(null) // Nonaktifkan klik untuk placeholder
        }
    }

    override fun getItemCount() = materials.size

    inner class ViewHolder(private val binding: RecentListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(material: SubMaterialModel) {
            binding.tvTitleCard.text = material.subMaterial
            Glide.with(binding.root.context)
                .load(material.learningImagePath)
                .into(binding.tvImgCard)
        }
    }
}
