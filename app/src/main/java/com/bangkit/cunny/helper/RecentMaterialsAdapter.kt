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
        materials.addAll(data)
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
        holder.itemView.setOnClickListener { onItemClickCallback?.invoke(material) }
    }

    override fun getItemCount() = materials.size

    inner class ViewHolder(private val binding: RecentListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(material: SubMaterialModel) {
            binding.tvTitleCard.text = material.subMaterial
            Glide.with(binding.root.context).load(material.learningImagePath).into(binding.tvImgCard)
        }
    }
}
