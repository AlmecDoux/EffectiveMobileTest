package com.effectivemobiletest.epoxy.contollers.customEpoxyViews

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
class PhotosSnappingHorizontalCarousel(context: Context): Carousel(context) {


    override fun createLayoutManager(): LayoutManager {
        val manager = LinearLayoutManager(context)
        manager.orientation = HORIZONTAL
        return manager
    }

}