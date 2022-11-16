package com.effectivemobiletest.adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.databinding.BestSellerItemLayoutBinding
import com.effectivemobiletest.adapters.adaptersModels.BestSellerAdapterModel
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapter
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem
import com.squareup.picasso.Picasso


class BestSellerAdapter(private val clickCategory: ((idCategory: Int) -> Unit)? = null):
    DelegateAdapter<BestSellerAdapterModel, BestSellerAdapter.BestSellerViewHolder>(
        BestSellerAdapterModel::class.java) {

    inner class BestSellerViewHolder(
        private val context: Context,
        private val binding: BestSellerItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BestSellerAdapterModel) {
            val bestSellerItem = item.content()
            with(binding){
                Picasso.with(context).load(bestSellerItem.pictureURL).into(imgBestSellerItem)
                discountPrice.text = bestSellerItem.discountPrice.toString()
                priceWithOutDiscount.text = bestSellerItem.priceWithoutDiscount.toString()
                productTitle.text = bestSellerItem.title
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = BestSellerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestSellerViewHolder(parent.context, binding)
    }

    override fun bindViewHolder(model: BestSellerAdapterModel,
                                viewHolder: BestSellerViewHolder,
                                payloads: List<DelegateAdapterItem.Payloadable>) {
        viewHolder.bind(model)
    }
}