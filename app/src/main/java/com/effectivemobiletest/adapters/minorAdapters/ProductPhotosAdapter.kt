package com.effectivemobiletest.adapters.minorAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.ProductDetailPhotoLayoutBinding
import com.squareup.picasso.Picasso

class ProductPhotosAdapter():Adapter<ProductPhotosAdapter.PhotosViewHolder>(){

    private var photos:ArrayList<String> = arrayListOf()

    inner class PhotosViewHolder(
        private val binding: ProductDetailPhotoLayoutBinding
    ):ViewHolder(binding.root){

        fun bind(photoUrl:String){
            val widthScreen = itemView.context.applicationContext.resources.displayMetrics.widthPixels
            val photoWidth = (widthScreen/2).toInt()
            val photoHeight = (photoWidth * 1.5).toInt()
            val layoutParams = LayoutParams(photoWidth, photoHeight)
            binding.root.layoutParams = layoutParams
            Picasso.get().load(photoUrl)
                .into(binding.imgProductPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = ProductDetailPhotoLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }
    fun setData(photos:ArrayList<String>){
        this.photos = photos
        notifyItemRangeChanged(0, photos.size)
    }
}