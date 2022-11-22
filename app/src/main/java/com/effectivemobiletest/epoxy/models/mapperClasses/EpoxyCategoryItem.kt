package com.effectivemobiletest.epoxy.models.mapperClasses

import com.effectivemobile.domain.models.subtypes.CategoryItem

data class EpoxyCategoryItem(
    val categoryItem: CategoryItem,
    var isSelected:Boolean
)