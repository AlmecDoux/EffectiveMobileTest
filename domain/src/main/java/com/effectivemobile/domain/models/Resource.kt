package com.effectivemobile.domain.models

sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null

) {
    class Success<T>(data: T) : Resource<T>(data)
    class DataError<T>(error: String) : Resource<T>(null, error)
}