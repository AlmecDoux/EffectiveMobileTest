package com.effectivemobiletest.adapters.minorAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BasketItemLayoutBinding
import com.effectivemobiletest.ui.pages.cartPage.BasketData
import com.squareup.picasso.Picasso

class BasketAdapter(private val basketItems:List<BasketData>):
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){

    inner class BasketViewHolder(
        private val binding: BasketItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(basketData: BasketData){
            binding.cartProductAmount.text = basketData.price
            binding.cartProductTitle.text = basketData.title
            Picasso.get().load(basketData.imageURL)
                .into(binding.imgCartProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketItems[position])
    }

    override fun getItemCount(): Int {
        return basketItems.size
    }

}