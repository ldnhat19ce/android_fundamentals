package com.ldnhat.repository.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ldnhat.repository.R
import com.ldnhat.repository.databinding.DevbyteItemBinding
import com.ldnhat.repository.domain.DevByteVideos

class DevByteAdapter(val callback : VideoClick) : RecyclerView.Adapter<DevByteAdapter.DevByteViewHolder>(){

    var videos: List<DevByteVideos> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding:DevbyteItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                DevByteViewHolder.LAYOUT, parent, false
            )

        return DevByteViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class DevByteViewHolder(val viewDataBinding : DevbyteItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root){

        companion object{
            @LayoutRes
            val LAYOUT = R.layout.devbyte_item
        }

    }
}

class VideoClick(val block: (DevByteVideos) -> Unit) {
    /**
     * Called when a video is clicked
     *
     * @param video the video that was clicked
     */
    fun onClick(video: DevByteVideos) = block(video)
}