package com.effectivemobile.domain.models.subtypes

import com.google.gson.annotations.SerializedName

data class BasketData(
    @SerializedName("id")
    val id:Int = 0,

    @SerializedName("images")
    val imageURL:String = "",

    @SerializedName("price")
    val price:Int = 0,

    @SerializedName("title")
    val title:String = "",
)
