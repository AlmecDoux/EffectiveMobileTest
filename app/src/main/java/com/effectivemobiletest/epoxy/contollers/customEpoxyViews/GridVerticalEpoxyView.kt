package com.effectivemobiletest.epoxy.contollers.customEpoxyViews

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class GridVerticalEpoxyView(context: Context): Carousel(context) {

    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2,
            GridLayoutManager.VERTICAL, false
        )
    }

}