package com.bangkit.cunny.helper

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.R
import com.bangkit.cunny.data.Material
import com.bangkit.cunny.data.response.LearningMaterial
import com.bangkit.cunny.ui.materials_detail.DetailActivity

class MaterialAdapter(private val listMaterial: ArrayList<LearningMaterial>) : RecyclerView.Adapter<MaterialAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun updateData(newList: ArrayList<LearningMaterial>) {
        Log.d("MaterialAdapter", "Updating data: ${newList.size} items")
        listMaterial.clear()
        listMaterial.addAll(newList)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name_card)
        val imgPhoto: ImageView = itemView.findViewById(R.id.tv_img_card)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.material_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val material = listMaterial[position]
        holder.tvName.text = material.title
        holder.tvDescription.text = material.description

        holder.imgPhoto.setImageResource(R.drawable.ic_place_holder)

        // Optional: Add click listener for the entire item (if you want to keep that functionality)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(material, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = listMaterial.size

    interface OnItemClickCallback {
        fun onItemClicked(data: LearningMaterial, position: Int)
    }
}