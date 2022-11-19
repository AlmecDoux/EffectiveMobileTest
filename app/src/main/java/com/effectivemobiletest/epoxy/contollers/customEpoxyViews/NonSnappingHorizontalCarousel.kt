package com.effectivemobiletest.epoxy.contollers.customEpoxyViews

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class NonSnappingHorizontalCarousel(context: Context):Carousel(context) {

    override fun createLayoutManager(): LayoutManager {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        return layoutManager
    }
    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null
    }
}