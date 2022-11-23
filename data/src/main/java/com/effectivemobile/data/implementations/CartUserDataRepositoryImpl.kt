package com.effectivemobile.data.implementations

import com.effectivemobile.data.ServiceGenerator
import com.effectivemobile.data.retrofit.interfaces.CartUserAPI
import com.effectivemobile.data.utils.processCall
import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.models.UserCartData
import com.effectivemobile.domain.repository.CartUserDataRepository
import javax.inject.Inject

class CartUserDataRepositoryImpl
@Inject constructor(
    serviceGenerator: ServiceGenerator
): CartUserDataRepository {
    private val service = serviceGenerator.createService(CartUserAPI::class.java)

    override suspend fun getUserCartData(): Resource<UserCartData> {
        return when (val response = processCall{
            service.getUserCart()}){
            is UserCartData -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(error = response.toString())
            }
        }
    }

}