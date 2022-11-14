package com.effectivemobile.domain.models.subtypes

import com.google.gson.annotations.SerializedName

data class BestSalesItem(
    @SerializedName("id")
    val id:Int = 0,

    @SerializedName("is_favorites")
    val isFavorites:Boolean = false,

    @SerializedName("title")
    val title:String = "",

    @SerializedName("price_without_discount")
    val priceWithoutDiscount:Int = 0,

    @SerializedName("discount_price")
    val discountPrice:Int = 0,

    @SerializedName("picture")
    val pictureURL:String = ""
)