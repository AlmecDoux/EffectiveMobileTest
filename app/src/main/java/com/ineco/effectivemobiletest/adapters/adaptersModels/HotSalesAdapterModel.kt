package com.ineco.effectivemobiletest.adapters.adaptersModels

import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.ineco.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem

class HotSalesAdapterModel(private val hotSaleItem: HotSalesItem): DelegateAdapterItem {

    override fun id(): Any = hotSaleItem.id

    override fun content(): HotSalesItem = hotSaleItem
}