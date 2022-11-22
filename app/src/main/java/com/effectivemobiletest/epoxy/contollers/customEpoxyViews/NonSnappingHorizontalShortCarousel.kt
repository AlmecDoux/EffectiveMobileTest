package com.effectivemobiletest.epoxy.contollers.customEpoxyViews

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_MATCH_HEIGHT)
class NonSnappingHorizontalShortCarousel(context: Context): Carousel(context) {

    override fun createLayoutManager(): LayoutManager {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        return layoutManager
    }
    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null
    }

}