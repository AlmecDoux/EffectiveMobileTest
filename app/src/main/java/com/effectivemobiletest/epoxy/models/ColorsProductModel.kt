package com.effectivemobiletest.epoxy.models

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.ColorProductLayoutBinding
import com.effectivemobiletest.App.Companion.outLogs

//class ColorsProductModel(
//    private val color: ColorsOfPoint,
//    private val listenerOfSelect:((selected:Boolean)->Unit)
//): ViewBindingKotlinModel<ColorProductLayoutBinding>(R.layout.color_product_layout) {
//
//
//    override fun ColorProductLayoutBinding.bind() {
//        val colorResource = Color.parseColor(color.color)
//        selectedFlag.visibility = if(color.isSelected) View.VISIBLE else View.GONE
//        colorField.backgroundTintList = ColorStateList.valueOf(colorResource)
//        root.setOnClickListener {
//            listenerOfSelect.invoke(color.isSelected)
//        }
//    }
//    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
//        return totalSpanCount
//    }
//
//}
