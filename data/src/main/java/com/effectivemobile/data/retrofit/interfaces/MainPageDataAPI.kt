package com.effectivemobile.data.retrofit.interfaces

import com.effectivemobile.domain.models.MainPageData
import retrofit2.Response
import retrofit2.http.GET

interface MainPageDataAPI {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getPageData() : Response<MainPageData>
}