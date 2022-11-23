package com.effectivemobiletest.adapters.adaptersData

import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobiletest.adapters.DisplayableItem

data class BestSalesAdapterItem(
    val items:List<BestSalesItem>,
    val clickOn:(itemId:Int)->Unit
):DisplayableItem
