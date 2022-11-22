package com.effectivemobiletest.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.HeaderTitleLayoutBinding

class HeaderTitleLayout(context: Context, attributeSet: AttributeSet):
    ConstraintLayout(context, attributeSet) {
    private var binding: HeaderTitleLayoutBinding
    init {
        binding = HeaderTitleLayoutBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.HeaderTitleLayout)
        val textTitle = typedArray.getString(R.styleable.HeaderTitleLayout_textHeader)
        val textLink = typedArray.getString(R.styleable.HeaderTitleLayout_textLink)
        typedArray.recycle()

        binding.headerTitle.text = textTitle
        binding.headerLink.text = textLink
    }
}