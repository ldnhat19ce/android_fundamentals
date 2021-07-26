package com.ldnhat.lesson8.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ldnhat.lesson8.databinding.GridViewItemBinding
import com.ldnhat.lesson8.network.MarsPropety

class PhotoGridAdapter : ListAdapter<MarsPropety, PhotoGridAdapter.MarPropertyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarPropertyViewHolder {

        return MarPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

    class MarPropertyViewHolder(private var binding : GridViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(marsProperty: MarsPropety){
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPropety>(){
        override fun areItemsTheSame(oldItem: MarsPropety, newItem: MarsPropety): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsPropety, newItem: MarsPropety): Boolean {
            return oldItem.id == newItem.id
        }
    }
}