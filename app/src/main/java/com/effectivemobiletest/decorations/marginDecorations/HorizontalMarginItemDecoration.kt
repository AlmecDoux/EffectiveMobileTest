package com.effectivemobiletest.decorations.marginDecorations

import android.graphics.Rect
import android.view.View
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginItemDecoration(private val spaceSize: Int)
    : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (view == parent[0]){
                left = spaceSize
            }
            else if(view == parent[parent.size - 1]) {
                right = spaceSize
            }
        }
    }
}