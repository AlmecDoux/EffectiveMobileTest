package com.effectivemobiletest.ui.views.bottomNavigationLayout

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BottomNavigationItemLayoutBinding
import com.effectivemobile.test.databinding.BottomNavigationLayoutBinding

class BottomNavigationItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
): LinearLayoutCompat(context, attributeSet, defStyle){
    var navigationItem: MenuItem? = null
    private var binding: BottomNavigationItemLayoutBinding
    init {
        binding = BottomNavigationItemLayoutBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
        val layoutParams = binding.root.layoutParams as LinearLayout.LayoutParams
        layoutParams.gravity = Gravity.CENTER
        layoutParams.weight = 1f
        setLayoutParams(layoutParams)
    }

    fun setNavigationMenuItem(navigationItem: MenuItem){
        this.navigationItem = navigationItem
        navigationItem.icon?.let {
            binding.bottomItemImg.setImageDrawable(it)
            binding.bottomItemImg.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
    }
    fun setBadge(value:Int){
        if(value <= 0){
            binding.badgeLabel.visibility = GONE
        }
        else{
            binding.badgeLabel.text = value.toString()
            binding.badgeLabel.visibility = VISIBLE
        }
    }
    fun getMenuDestination():Int{
        return navigationItem?.itemId?:-1
    }

    override fun setSelected(selected: Boolean) {
        if(selected){
            binding.bottomItemImg.setColorFilter(ContextCompat.getColor(context, R.color.primaryColor))
        }
        else{
            binding.bottomItemImg.setColorFilter(ContextCompat.getColor(context, R.color.white))
        }
        super.setSelected(selected)
    }

}