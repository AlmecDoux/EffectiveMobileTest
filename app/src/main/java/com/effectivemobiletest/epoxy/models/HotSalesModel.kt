package com.effectivemobiletest.epoxy.models

import android.view.View
import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BlockHotSaleItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HotSalesModel(
    private val hotSalesItem: HotSalesItem
): ViewBindingKotlinModel<BlockHotSaleItemLayoutBinding>(R.layout.block_hot_sale_item_layout) {

    override fun BlockHotSaleItemLayoutBinding.bind() {
        titleHotSaleItem.text = hotSalesItem.title
        subTitleHotSaleItem.text = hotSalesItem.subTitle
        iconNew.visibility = if(hotSalesItem.isNew) View.VISIBLE else View.GONE
        Picasso.get().load(hotSalesItem.pictureURL).into(imgHotSaleItem)
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}