package com.effectivemobile.domain.repository

import com.effectivemobile.domain.models.ProductDetails
import com.effectivemobile.domain.models.Resource

interface ProductDetailsRepository {
    suspend fun getProductDetail(): Resource<ProductDetails>
}