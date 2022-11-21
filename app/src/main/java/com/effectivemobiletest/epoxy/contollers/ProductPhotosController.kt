package com.effectivemobiletest.epoxy.contollers

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.effectivemobiletest.decorations.marginDecorations.CenterZoomLinearLayoutManager
import com.effectivemobiletest.epoxy.contollers.customEpoxyViews.photosSnappingHorizontalCarousel
import com.effectivemobiletest.epoxy.models.ProductPhotoModel


class ProductPhotosController: TypedEpoxyController<ArrayList<String>>() {



    private fun buildPhotosCarousel(data: ArrayList<String>) {
        if (data.isEmpty()) return
        val items = data.map {
            ProductPhotoModel(it)
                .id(it)
        }
        carousel {
            id("horizontal")
            hasFixedSize(true)
            models(items)
            onBind { _, carousel, id ->
                carousel.layoutManager = CenterZoomLinearLayoutManager(carousel.context)
                carousel.overScrollMode = View.OVER_SCROLL_NEVER
                val snapHelper = LinearSnapHelper()
                carousel.scrollToPosition(1)
                carousel.post {
                    val viewByPosition = (carousel.layoutManager as RecyclerView.LayoutManager ).findViewByPosition(1)
                    viewByPosition?.let {
                        val snapDistance = snapHelper.calculateDistanceToFinalSnap((carousel.layoutManager as RecyclerView.LayoutManager ), it)
                        if (snapDistance?.get(0) != 0 || snapDistance[1] != 0) {
                            carousel.scrollBy(snapDistance?.get(0)!!,
                                snapDistance[1]
                            )
                        }
                    }
                }
            }
        }

    }

    override fun buildModels(data: ArrayList<String>?) {
        data?.let {
            buildPhotosCarousel(it)
        }
    }

}