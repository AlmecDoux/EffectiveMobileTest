package com.effectivemobiletest.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun <T> MutableLiveData<T>.set(newValue: T) = apply { setValue(newValue) }

fun <T> MutableLiveData<T>.post(newValue: T) = apply { postValue(newValue) }

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this