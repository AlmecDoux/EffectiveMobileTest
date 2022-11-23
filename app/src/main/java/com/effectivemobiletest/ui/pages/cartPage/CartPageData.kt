package com.effectivemobiletest.ui.pages.cartPage

import com.effectivemobile.domain.models.UserCartData
import com.effectivemobiletest.adapters.DisplayableItem
import com.effectivemobiletest.utils.AmountDecorator
import com.google.gson.annotations.SerializedName

data class CartPageData(
    val delivery:String = "",
    val totalPrice:String = "",
    val basket: List<BasketData>
)


data class BasketData(
    val imageURL:String = "",
    val price:String = "",
    val title:String = "",
):DisplayableItem

fun UserCartData.mapToPageData():CartPageData{
    return CartPageData(
        delivery = this.delivery,
        totalPrice = AmountDecorator.getNormalAmountToStringWithCurrency(this.totalPrice, 2),
        basket = this.basket.map { basket->
            BasketData(
                imageURL = basket.imageURL,
                price =  AmountDecorator.getNormalAmountToStringWithCurrency(basket.price, 2),
                title = basket.title
            )
        }
    )
}