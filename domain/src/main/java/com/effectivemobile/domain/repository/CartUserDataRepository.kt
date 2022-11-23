package com.effectivemobile.domain.repository

import com.effectivemobile.domain.models.Resource
import com.effectivemobile.domain.models.UserCartData

interface CartUserDataRepository {
    suspend fun getUserCartData(): Resource<UserCartData>
}