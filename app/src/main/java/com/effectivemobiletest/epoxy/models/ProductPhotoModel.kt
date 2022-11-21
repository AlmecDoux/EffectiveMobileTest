package com.effectivemobiletest.epoxy.models

import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.ProductDetailPhotoLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class ProductPhotoModel(
    private val photoUrl: String
): ViewBindingKotlinModel<ProductDetailPhotoLayoutBinding>(R.layout.product_detail_photo_layout) {

    override fun ProductDetailPhotoLayoutBinding.bind() {
        Picasso.get().load(photoUrl).into(imgProductPhoto)
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}