package com.effectivemobiletest.epoxy.models.mapperClasses

import com.effectivemobile.domain.models.subtypes.HotSalesItem

data class EpoxyHotSalesItem(
    val hotSalesItems: List<HotSalesItem>
):EpoxyData()

fun List<HotSalesItem>.mapToEpoxy():EpoxyHotSalesItem{
    return EpoxyHotSalesItem(
        hotSalesItems = this.map {
            it
        }
    )
}
