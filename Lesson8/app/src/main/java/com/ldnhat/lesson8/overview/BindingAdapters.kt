package com.ldnhat.lesson8.overview

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ldnhat.lesson8.R
import com.ldnhat.lesson8.network.MarsPropety

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl : String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context).load(imgUri).apply(RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
        ).into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<MarsPropety>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status : MarsApiStatus?){
    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("isRental")
fun bindRental(rentalImageView: ImageView, isRental : Boolean){
    if (isRental){
        rentalImageView.visibility = View.VISIBLE
    }else{
        rentalImageView.visibility = View.GONE
    }
}