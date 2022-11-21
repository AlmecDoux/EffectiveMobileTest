package com.effectivemobile.data.retrofit.interfaces

import com.effectivemobile.domain.models.ProductDetails
import retrofit2.Response
import retrofit2.http.GET


interface DetailsProductAPI {
    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetails() : Response<ProductDetails>
}