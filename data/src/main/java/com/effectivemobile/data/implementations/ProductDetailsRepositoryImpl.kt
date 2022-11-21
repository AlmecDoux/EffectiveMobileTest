package com.effectivemobile.data.implementations

import com.effectivemobile.data.ServiceGenerator
import com.effectivemobile.data.retrofit.interfaces.DetailsProductAPI
import com.effectivemobile.data.utils.processCall
import com.effectivemobile.domain.models.MainPageData
import com.effectivemobile.domain.models.ProductDetails
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class ProductDetailsRepositoryImpl
@Inject constructor(
    serviceGenerator: ServiceGenerator
): ProductDetailsRepository {

    private val service = serviceGenerator.createService(DetailsProductAPI::class.java)

    override suspend fun getProductDetail(): Resource<ProductDetails> {
        return when (val response = processCall{
            service.getProductDetails()}){
            is ProductDetails -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(error = response.toString())
            }
        }
    }
}