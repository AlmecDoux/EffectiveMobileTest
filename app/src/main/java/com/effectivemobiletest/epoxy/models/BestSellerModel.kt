package com.effectivemobiletest.epoxy.models

import android.text.Html
import androidx.core.text.HtmlCompat
import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BlockBestSellerItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.effectivemobiletest.utils.AmountDecorator
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
        val withOutPriceAmount = AmountDecorator.getNormalAmountToStringWithCurrency(bestSalesItem.priceWithoutDiscount)
        val priceAmount = AmountDecorator.getNormalAmountToStringWithCurrency(bestSalesItem.discountPrice)
        discountPrice.text = priceAmount
        priceWithOutDiscount.text = HtmlCompat.fromHtml("<s>$withOutPriceAmount</s>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        productTitle.text = bestSalesItem.title
        root.setOnClickListener {
            clickOn.invoke(bestSalesItem.id)
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount/2
    }
}