package com.effectivemobile.domain.models.subtypes

import com.google.gson.annotations.SerializedName

data class HotSalesItem(

    @SerializedName("id")
    val id:Int = 0,

    @SerializedName("is_new")
    val isNew:Boolean = false,

    @SerializedName("title")
    val title:String = "",

    @SerializedName("subtitle")
    val subTitle:String = "",

    @SerializedName("picture")
    val pictureURL:String = "",

    @SerializedName("is_buy")
    val isBuy:Boolean = false
)
