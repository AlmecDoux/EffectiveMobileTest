package com.effectivemobiletest.di

import com.effectivemobile.data.implementations.CartUserDataRepositoryImpl
import com.effectivemobile.data.implementations.MainPageDataRepositoryImpl
import com.effectivemobile.data.implementations.ProductDetailsRepositoryImpl
import com.effectivemobile.domain.repository.CartUserDataRepository
import com.effectivemobile.domain.repository.MainPageDataRepository
import com.effectivemobile.domain.repository.ProductDetailsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMainPageDataRepository(mainPageDataRepositoryImpl: MainPageDataRepositoryImpl):
            MainPageDataRepository

    @Binds
    abstract fun provideProductDetailsRepository(productDetailsRepositoryImpl: ProductDetailsRepositoryImpl):
            ProductDetailsRepository

    @Binds
    abstract fun provideCartUserDataRepository(cartUserDataRepositoryImpl: CartUserDataRepositoryImpl):
            CartUserDataRepository

}