package com.effectivemobile.domain.models

import com.effectivemobile.domain.models.subtypes.BasketData
import com.google.gson.annotations.SerializedName

data class UserCartData(
    @SerializedName("id")
    val id:Int = 0,

    @SerializedName("basket")
    val basket:ArrayList<BasketData> = arrayListOf(),

    @SerializedName("delivery")
    val delivery:Boolean = false,

    @SerializedName("total")
    val totalPrice:Int = 0,
)
