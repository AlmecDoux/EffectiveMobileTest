package com.effectivemobiletest.epoxy.models.mapperClasses

import com.effectivemobile.domain.models.subtypes.CategoryItem

data class EpoxyCategoryItems(
    val categoryItems: List<EpoxyCategoryItem>,
    val clickOn:(idCategory:Int)->Unit
):EpoxyData()

fun List<CategoryItem>.mapToEpoxy(clickOn:(idCategory:Int)->Unit):EpoxyCategoryItems{
    return EpoxyCategoryItems(
        categoryItems = this.mapIndexed {id, it->
            EpoxyCategoryItem(it, isSelected = id == 0)
        },
        clickOn = clickOn
    )
}

