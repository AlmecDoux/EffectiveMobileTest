package com.effectivemobiletest.epoxy.contollers

import com.airbnb.epoxy.TypedEpoxyController
import com.effectivemobiletest.epoxy.models.ProductPhotoModel

class ProductPhotosController:TypedEpoxyController<ArrayList<String>>() {

    override fun buildModels(data: ArrayList<String>?) {
        data?.let {
            buildPhotosCarousel(data)
        }
    }

    private fun buildPhotosCarousel(data: ArrayList<String>) {
        if (data.isEmpty()) return
        data.forEach {
            ProductPhotoModel(it)
                .id(it)
                .addTo(this)
        }
    }
}