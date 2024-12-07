package com.bangkit.cunny.helper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.R
import com.bangkit.cunny.data.response.LearningMaterial

class SubMaterialAdapter(private val listMaterial: ArrayList<LearningMaterial>) : RecyclerView.Adapter<SubMaterialAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun updateData(newList: ArrayList<LearningMaterial>) {
        Log.d("SubMaterialAdapter", "Updating data: ${newList.size} items")
        listMaterial.clear()
        listMaterial.addAll(newList)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name_card)
        val imgPhoto: ImageView = itemView.findViewById(R.id.tv_img_card)
        val tvSubMaterial: TextView = itemView.findViewById(R.id.tv_sub_materials)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.sub_material_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val material = listMaterial[position]
        holder.tvName.text = material.title

        // Mengolah subMaterials menjadi String untuk ditampilkan
        val subMaterialsText = material.subMaterials?.flatMap { it.orEmpty() }
            ?.filterNotNull()
            ?.joinToString(", ") ?: "No sub-materials available"

        holder.tvSubMaterial.text = subMaterialsText

        holder.imgPhoto.setImageResource(R.drawable.ic_place_holder)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(material, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = listMaterial.size

    interface OnItemClickCallback {
        fun onItemClicked(data: LearningMaterial, position: Int)
    }
}