package com.effectivemobile.data.retrofit.interfaces

import com.effectivemobile.domain.models.UserCartData
import retrofit2.Response
import retrofit2.http.GET

interface CartUserAPI {
    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getUserCart() : Response<UserCartData>
}