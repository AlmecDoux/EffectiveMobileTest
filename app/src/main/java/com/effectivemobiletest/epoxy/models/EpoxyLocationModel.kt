package com.effectivemobiletest.epoxy.models

import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.LocationItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel

class EpoxyLocationModel(
    private val location: String,
    private val clickOnFilter:()->Unit,
    val chooseLocation:()->Unit
): ViewBindingKotlinModel<LocationItemLayoutBinding>(R.layout.location_item_layout) {

    override fun LocationItemLayoutBinding.bind() {
        btnFilter.setOnClickListener {
            clickOnFilter.invoke()
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}