package com.effectivemobile.domain.models

import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.google.gson.annotations.SerializedName

data class MainPageData(
    @SerializedName("home_store")
    val hotSalesItems:ArrayList<HotSalesItem> = arrayListOf(),

    @SerializedName("best_seller")
    val bestSellerItems:ArrayList<BestSalesItem> = arrayListOf()
)
