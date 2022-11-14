package com.effectivemobile.domain.repository

import com.effectivemobile.domain.models.MainPageData
import com.effectivemobile.domain.models.Resource

interface MainPageDataRepository {
    suspend fun getHomePageData(): Resource<MainPageData>
}