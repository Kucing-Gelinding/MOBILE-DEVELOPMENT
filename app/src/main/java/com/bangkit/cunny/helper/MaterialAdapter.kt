package com.bangkit.cunny.helper

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.R
import com.bangkit.cunny.data.Material
import com.bangkit.cunny.ui.materials_detail.DetailActivity

class MaterialAdapter(private val listMaterial: ArrayList<Material>) : RecyclerView.Adapter<MaterialAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
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
        val (name, description, photo) = listMaterial[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        // Set up the click listener for the More Info button
        holder.itemView.findViewById<ImageView>(R.id.tv_img_card).setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("player_position", position)
            holder.itemView.context.startActivity(intentDetail)
        }



        holder.tvName.setOnClickListener {
            onItemClickCallback.onItemClicked(listMaterial[holder.adapterPosition], holder.adapterPosition)
        }
        holder.imgPhoto.setOnClickListener {
            onItemClickCallback.onItemClicked(listMaterial[holder.adapterPosition], holder.adapterPosition)
        }
        // Optional: Add click listener for the entire item (if you want to keep that functionality)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMaterial[holder.adapterPosition], holder.adapterPosition) // Pass position in callback
        }
    }

    override fun getItemCount(): Int = listMaterial.size

    // Modify the callback interface to also include the position
    interface OnItemClickCallback {
        fun onItemClicked(data: Material, position: Int) // Pass the position
    }
}