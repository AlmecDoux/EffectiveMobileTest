package com.effectivemobile.domain.useCases

import com.effectivemobile.domain.annotations.IoDispatcher
import com.effectivemobile.domain.models.MainPageData
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.repository.CartUserDataRepository
import com.effectivemobile.domain.repository.MainPageDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMainPageDataUseCase
@Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val mainPageDataRepository: MainPageDataRepository,
    private val cartUserDataRepository: CartUserDataRepository
) {

    suspend fun getMainPageData(): Flow<Resource<MainPageData>>{
        return withContext(ioDispatcher) {
            flow {
                emit(mainPageDataRepository.getHomePageData())
            }
        }
    }
    suspend fun getCountProductInUserCart(): Flow<Int>{
        return withContext(ioDispatcher) {
            flow {
                val response = cartUserDataRepository.getUserCartData()
                response.data?.let {
                    emit(it.basket.size)
                }
                response.error?.let {
                    emit(0)
                }
            }
        }
    }
}