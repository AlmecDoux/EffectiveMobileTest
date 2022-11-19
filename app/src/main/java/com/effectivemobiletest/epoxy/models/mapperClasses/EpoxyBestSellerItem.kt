package com.effectivemobiletest.epoxy.models.mapperClasses

import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobile.domain.models.subtypes.CategoryItem

data class EpoxyBestSellerItem(
    val bestSellerItems:List<BestSalesItem>,
    val clickFavorite:(id:Int, isFavorite:Boolean)->Unit,
    val clickOn:(itemId:Int)->Unit
):EpoxyData()

fun List<BestSalesItem>.mapToEpoxy(clickOn:(itemId:Int)->Unit, clickFavorite:(id:Int, isFavorite:Boolean)->Unit):EpoxyBestSellerItem{
    return EpoxyBestSellerItem(
        bestSellerItems = this.map {
            it
        },
        clickOn = clickOn,
        clickFavorite = clickFavorite
    )
}
