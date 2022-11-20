package com.effectivemobiletest.epoxy.models

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.HeaderTitleLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyHeaderTitleItem
import com.jackandphantom.carouselrecyclerview.view.ReflectionViewContainer

class HeaderTitleModel(
    private val headerTitleItem: EpoxyHeaderTitleItem
): ViewBindingKotlinModel<HeaderTitleLayoutBinding>(R.layout.header_title_layout) {

    override fun HeaderTitleLayoutBinding.bind() {

        headerTitle.text = headerTitleItem.headerTitle
        headerLink.text = headerTitleItem.linkText
        headerLink.setOnClickListener {
            headerTitleItem.clickLink.invoke()
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}