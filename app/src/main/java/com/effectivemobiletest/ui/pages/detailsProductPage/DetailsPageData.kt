package com.effectivemobiletest.ui.pages.detailsProductPage

import com.effectivemobile.domain.models.ProductDetails
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyData
import com.effectivemobiletest.epoxy.models.mapperClasses.mapToCapacityItem
import com.effectivemobiletest.epoxy.models.mapperClasses.mapToColorOfPoint
import com.effectivemobiletest.utils.AmountDecorator

data class DetailsPageData(
    val colorAndCapacityData:ArrayList<EpoxyData>,
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
        colorAndCapacityData = arrayListOf(
                this.color.mapToColorOfPoint(),
                this.capacity.mapToCapacityItem()),
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


