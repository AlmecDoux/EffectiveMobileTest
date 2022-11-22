package com.effectivemobiletest.epoxy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.effectivemobile.test.databinding.ProductDetailPhotoLayoutBinding
import com.squareup.picasso.Picasso

class PhotosAdapter():Adapter<PhotosAdapter.PhotosViewHolder>(){

    private var photos:ArrayList<String> = arrayListOf()

    inner class PhotosViewHolder(
        private val binding: ProductDetailPhotoLayoutBinding
    ):ViewHolder(binding.root){

        fun bind(photoUrl:String){
            Picasso.get().load(photoUrl).into(binding.imgProductPhoto)
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