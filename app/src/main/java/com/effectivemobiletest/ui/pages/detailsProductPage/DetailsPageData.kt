package com.effectivemobiletest.ui.pages.detailsProductPage

import com.effectivemobile.domain.models.ProductDetails
import com.effectivemobiletest.adapters.DisplayableItem
import com.effectivemobiletest.adapters.adaptersData.ProductCapacityAdapterItem
import com.effectivemobiletest.adapters.adaptersData.ProductColorsAdapterItem
import com.effectivemobiletest.utils.AmountDecorator

data class DetailsPageData(
    val colorAndCapacityData:List<DisplayableItem>,
    val photos:ArrayList<String>,
    var CPU: String= "",
    var isFavorites : Boolean = false,
    var price: String = "",
    var rating: Float = 0.0f,
    var sd: String = "",
    var ssd: String = "",
    var camera:String = "",
    var title: String = ""
)

fun ProductDetails.mapToDetailsData():DetailsPageData{
    return DetailsPageData(
        colorAndCapacityData = listOf(
            ProductColorsAdapterItem(this.color),
            ProductCapacityAdapterItem(this.capacity)
        ),
        CPU = this.CPU,
        photos = this.images,
        isFavorites = this.isFavorites,
        price = AmountDecorator.getNormalAmountToStringWithCurrency(this.price, 2),
        rating = this.rating.toFloat(),
        sd = this.sd,
        ssd = this.ssd,
        title = this.title,
        camera = this.camera
    )
}


