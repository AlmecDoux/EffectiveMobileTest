package com.effectivemobile.domain.useCases

import com.effectivemobile.domain.annotations.IoDispatcher
import com.effectivemobile.domain.models.MainPageData
import com.effectivemobile.domain.models.ProductDetails
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.repository.MainPageDataRepository
import com.effectivemobile.domain.repository.ProductDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductDetailsUseCase
@Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val productDetailsRepository: ProductDetailsRepository
){

    suspend fun getProductDetails(): Flow<Resource<ProductDetails>> {
        return withContext(ioDispatcher) {
            flow {
                emit(productDetailsRepository.getProductDetail())
            }
        }
    }

}