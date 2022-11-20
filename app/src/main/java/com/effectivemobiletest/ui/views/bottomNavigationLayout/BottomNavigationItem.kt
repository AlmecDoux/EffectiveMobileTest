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

        val size = (25 * factor).toInt()
        val layoutParams = LinearLayoutCompat.LayoutParams(size, size)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f
        setLayoutParams(layoutParams)
        //scaleType = ScaleType.CENTER_CROP
    }

    fun setNavigationMenuItem(navigationItem: MenuItem){
        this.navigationItem = navigationItem
        navigationItem.icon?.let {
            setImageDrawable(it)
            scaleType = ScaleType.CENTER_INSIDE
        }
    }
    fun getMenuDestination():Int{
        return navigationItem?.itemId?:-1
    }

    override fun setSelected(selected: Boolean) {
        if(selected){
            setColorFilter(ContextCompat.getColor(context, R.color.primaryColor))
        }
        else{
            setColorFilter(ContextCompat.getColor(context, R.color.white))
        }
        super.setSelected(selected)
    }

}