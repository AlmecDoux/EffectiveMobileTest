package com.effectivemobiletest.adapters.adaptersModels

import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem

class CategoriesAdapterModel(private val categoryItem: CategoryItem): DelegateAdapterItem {

    override fun id(): Any = categoryItem.id

    override fun content(): CategoryItem = categoryItem
}