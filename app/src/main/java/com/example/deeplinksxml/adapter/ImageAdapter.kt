package com.example.deeplinksxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deeplinksxml.R
import com.example.deeplinksxml.databinding.ListScreenRecyclerRowBinding
import com.example.deeplinksxml.model.Hit
import com.example.deeplinksxml.util.loadImageUrl

class ImageAdapter(
    private val imageList: List<Hit>, private val onClickListener: OnClickListener
) : ListAdapter<Hit, ImageAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_screen_recycler_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(imageList[position])

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListScreenRecyclerRowBinding.bind(itemView)
        fun bind(image: Hit) {
            image.previewURL.let {
                binding.favoritesListRecyclerViewSongImageView.loadImageUrl(it)
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (imageList: Hit) -> Unit) {
        fun onClick(image: Hit) = clickListener(image)
    }

}