package com.effectivemobiletest.di

import com.effectivemobile.data.implementations.MainPageDataRepositoryImpl
import com.effectivemobile.domain.repository.MainPageDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMainPageDataRepository(mainPageDataRepositoryImpl: MainPageDataRepositoryImpl):
            MainPageDataRepository
}