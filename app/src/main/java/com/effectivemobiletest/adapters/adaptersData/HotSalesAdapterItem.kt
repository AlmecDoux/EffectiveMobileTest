package com.effectivemobiletest.adapters.adaptersData

import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.effectivemobiletest.adapters.DisplayableItem

data class HotSalesAdapterItem(
    val hotSalesItems: List<HotSalesItem>
): DisplayableItem
