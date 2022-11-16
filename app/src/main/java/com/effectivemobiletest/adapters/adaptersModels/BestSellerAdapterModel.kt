package com.effectivemobiletest.adapters.adaptersModels

import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem

class BestSellerAdapterModel(private val bestSalesItem: BestSalesItem): DelegateAdapterItem {

    override fun id(): Any = bestSalesItem.id

    override fun content(): BestSalesItem = bestSalesItem
}