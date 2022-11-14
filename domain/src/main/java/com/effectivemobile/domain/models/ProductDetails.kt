package com.effectivemobile.domain.models

import com.google.gson.annotations.SerializedName

data class ProductDetails(

    @SerializedName("id")
    var id: String = "",

    @SerializedName("CPU")
    var CPU: String= "",

    @SerializedName("camera")
    var camera: String = "",

    @SerializedName("capacity")
    var capacity: ArrayList<String> = arrayListOf(),

    @SerializedName("color")
    var color: ArrayList<String> = arrayListOf(),

    @SerializedName("images")
    var images: ArrayList<String> = arrayListOf(),

    @SerializedName("isFavorites")
    var isFavorites : Boolean = false,

    @SerializedName("price")
    var price: Int = 0,

    @SerializedName("rating")
    var rating: Double = 0.0,

    @SerializedName("sd")
    var sd: String = "",

    @SerializedName("ssd")
    var ssd: String = "",

    @SerializedName("title")
    var title: String = ""
)
