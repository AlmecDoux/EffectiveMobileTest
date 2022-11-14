package com.effectivemobile.data

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator
@Inject constructor(
    private val retrofit: Retrofit
){

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}