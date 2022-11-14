package com.effectivemobiletest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named(DiConstant.URL)
    fun providesBaseUrl() : String = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideRetrofit(@Named(DiConstant.URL) BASE_URL : String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

}