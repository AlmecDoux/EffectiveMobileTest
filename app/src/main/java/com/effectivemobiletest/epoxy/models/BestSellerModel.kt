package com.effectivemobiletest.epoxy.models

import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BlockBestSellerItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class BestSellerModel(
    private val bestSalesItem: BestSalesItem,
    private val clickFavorite:(id:Int, isFavorite:Boolean)->Unit,
    private val clickOn:(id:Int)->Unit
): ViewBindingKotlinModel<BlockBestSellerItemLayoutBinding>(R.layout.block_best_seller_item_layout) {

    override fun BlockBestSellerItemLayoutBinding.bind() {
        favoriteBestSellerItem.isSelected = bestSalesItem.isFavorites
        favoriteBestSellerItem.setOnClickListener{
            val isSelected = !favoriteBestSellerItem.isSelected
            clickFavorite.invoke(bestSalesItem.id, isSelected)
            favoriteBestSellerItem.isSelected = isSelected
        }
        Picasso.get().load(bestSalesItem.pictureURL).into(imgBestSellerItem)
        discountPrice.text = bestSalesItem.discountPrice.toString()
        priceWithOutDiscount.text = bestSalesItem.priceWithoutDiscount.toString()
        productTitle.text = bestSalesItem.title
        root.setOnClickListener {
            clickOn.invoke(bestSalesItem.id)
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount/2
    }
}