package com.effectivemobiletest.epoxy.models.mapperClasses

import com.effectivemobile.domain.models.subtypes.CategoryItem

data class EpoxyCategoryItems(
    val categoryItem: List<CategoryItem>,
    val clickOn:(idCategory:Int)->Unit
):EpoxyData()

fun List<CategoryItem>.mapToEpoxy(clickOn:(idCategory:Int)->Unit):EpoxyCategoryItems{
    return EpoxyCategoryItems(
        categoryItem = this.map {
            it
        },
        clickOn = clickOn
    )
}

