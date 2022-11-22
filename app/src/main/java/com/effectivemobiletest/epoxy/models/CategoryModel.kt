package com.effectivemobiletest.epoxy.models

import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BlockCategoryItemLayoutBinding
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyCategoryItem

class CategoryModel(
    private var categoryItem: EpoxyCategoryItem,
    private val listenerOfSelect:((selected:Boolean,idCategory:Int)->Unit)
): ViewBindingKotlinModel<BlockCategoryItemLayoutBinding>(R.layout.block_category_item_layout) {

    override fun BlockCategoryItemLayoutBinding.bind() {
        outLogs(categoryItem.categoryItem.id.toString())
        categoryItem.categoryItem.img?.let {imageResource->
            categoryImg.setImageResource(imageResource)
        }
        val selected = categoryItem.isSelected
        categoryImg.isSelected = selected
        categoryName.isSelected = selected
        categoryName.text = categoryItem.categoryItem.title
        root.setOnClickListener {
            listenerOfSelect.invoke(categoryItem.isSelected, categoryItem.categoryItem.id)
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}