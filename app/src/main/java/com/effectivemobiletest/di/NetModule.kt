package com.effectivemobiletest.di

import android.app.Application
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

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
