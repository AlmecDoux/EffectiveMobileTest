package com.effectivemobiletest.ui.views.bottomNavigationLayout

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.effectivemobile.test.R

class BottomNavigationItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageView(context, attributeSet, defStyle){
    var navigationItem: MenuItem? = null
    private val factor = context.resources.displayMetrics.density
    init {

        val size = (35 * factor).toInt()
        val margin = (5 * factor).toInt()
        elevation = 5f
        val layoutParams = LinearLayoutCompat.LayoutParams(size, size)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f
        layoutParams.setMargins(margin, margin,margin, margin)
        setPadding(margin,margin, margin, margin)
        setLayoutParams(layoutParams)

    }

    fun setNavigationMenuItem(navigationItem: MenuItem){
        this.navigationItem = navigationItem
        navigationItem.icon?.let {
            setImageDrawable(it)
            scaleType = ScaleType.FIT_CENTER
        }
    }
    fun getMenuDestination():Int{
        return navigationItem?.itemId?:-1
    }
}