package com.effectivemobiletest.epoxy.models

import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BlockCategoryItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel

class CategoryModel(
    private val categoryItem: CategoryItem,
    private val clickOn:(idCategory:Int)->Unit
): ViewBindingKotlinModel<BlockCategoryItemLayoutBinding>(R.layout.block_category_item_layout) {

    override fun BlockCategoryItemLayoutBinding.bind() {
        categoryItem.img?.let {imageResource->
            categoryImg.setImageResource(imageResource)
        }
        categoryName.text = categoryItem.title
        root.setOnClickListener {
            clickOn.invoke(categoryItem.id)
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}