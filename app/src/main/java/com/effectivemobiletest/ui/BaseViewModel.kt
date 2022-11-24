package com.effectivemobiletest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.LoadingActions
import com.effectivemobiletest.events.NavigationActions
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.default

open class BaseViewModel: ViewModel() {

    protected val _navigateEvent = MutableLiveData<Event<NavigationActions>>()
    val navigateEvent: LiveData<Event<NavigationActions>> = _navigateEvent

    protected val _loading = MutableLiveData<Event<LoadingActions>>().default(
        initialValue = Event(LoadingActions.HideLoading)
    )
    val loading = _loading.asLiveData()
}