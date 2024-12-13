package com.bangkit.cunny.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.cunny.R
import com.bangkit.cunny.data.database.BookmarkModel
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val bookmarks = ArrayList<BookmarkModel>()
    private var onItemClickCallback: ((BookmarkModel) -> Unit)? = null

    fun setOnItemClickCallback(callback: (BookmarkModel) -> Unit) {
        onItemClickCallback = callback
    }

    fun submitList(newBookmarks: List<BookmarkModel>) {
        bookmarks.clear()
        bookmarks.addAll(newBookmarks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bookmark_list, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = bookmarks[position]
        holder.bind(bookmark)

        // Set item click listener
        holder.itemView.setOnClickListener {
            onItemClickCallback?.invoke(bookmark)
        }
    }

    override fun getItemCount() = bookmarks.size

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title_card)
        private val description: TextView = itemView.findViewById(R.id.tv_description_card)
        private val imageView: ShapeableImageView = itemView.findViewById(R.id.tv_img_card) // Menambahkan image view

        fun bind(bookmark: BookmarkModel) {
            title.text = bookmark.title
            description.text = bookmark.description
            // Memuat gambar menggunakan Glide
            Glide.with(itemView.context)
                .load(bookmark.learningImagePath)
                .placeholder(R.drawable.image_placeholder) // Menambahkan placeholder jika gambar tidak tersedia
                .into(imageView)
        }
    }
}

