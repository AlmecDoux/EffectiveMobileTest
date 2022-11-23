package com.effectivemobile.domain.useCases

import com.effectivemobile.domain.annotations.IoDispatcher
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.models.UserCartData
import com.effectivemobile.domain.repository.CartUserDataRepository
import com.effectivemobile.domain.repository.ProductDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductUserCartUseCase
@Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val cartUserDataRepository: CartUserDataRepository
){

    suspend fun getUserCart(): Flow<Resource<UserCartData>> {
        return withContext(ioDispatcher) {
            flow {
                emit(cartUserDataRepository.getUserCartData())
            }
        }
    }
}