package com.effectivemobile.data.implementations

import com.effectivemobile.data.ServiceGenerator
import com.effectivemobile.data.retrofit.interfaces.MainPageDataAPI
import com.effectivemobile.data.utils.processCall
import com.effectivemobile.domain.models.MainPageData
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.repository.MainPageDataRepository
import javax.inject.Inject

class MainPageDataRepositoryImpl
@Inject constructor(
    serviceGenerator: ServiceGenerator
): MainPageDataRepository {
    private val service = serviceGenerator.createService(MainPageDataAPI::class.java)

    override suspend fun getHomePageData(): Resource<MainPageData> {
        return when (val response = processCall{
            service.getPageData()}){
            is MainPageData -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(error = response.toString())
            }
        }
    }

}