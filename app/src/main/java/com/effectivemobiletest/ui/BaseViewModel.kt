package com.effectivemobiletest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.NavigationActions

class BaseViewModel:ViewModel() {
    protected val navigateEventPrivate = MutableLiveData<Event<NavigationActions>>()
    val navigateEvent: LiveData<Event<NavigationActions>> = navigateEventPrivate
}