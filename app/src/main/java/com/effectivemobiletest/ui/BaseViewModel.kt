package com.effectivemobiletest.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.LoadingActions
import com.effectivemobiletest.events.NavigationActions
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.default
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel
@Inject constructor(): ViewModel() {

    @SuppressLint("StaticFieldLeak")
    @Inject
    @ApplicationContext
    lateinit var appContext:Context

    protected val _navigateEvent = MutableLiveData<Event<NavigationActions>>()
    val navigateEvent: LiveData<Event<NavigationActions>> = _navigateEvent

    protected val _loading = MutableLiveData<Event<LoadingActions>>().default(
        initialValue = Event(LoadingActions.HideLoading)
    )
    val loading = _loading.asLiveData()
}