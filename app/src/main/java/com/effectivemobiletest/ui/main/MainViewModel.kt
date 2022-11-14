package com.effectivemobiletest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivemobile.test.R
import com.effectivemobiletest.App
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.default
import com.effectivemobiletest.extensions.post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(

): ViewModel() {

    private val _startDestination = MutableLiveData<Event<Int?>>()
        .default(initialValue = Event(null))
    val startDestination = _startDestination.asLiveData()

    private val topLevelDestinations = setOf(getTabsDestination())

    init {
        viewModelScope.launch {
            delay(3000)
            _startDestination.post(Event(getTabsDestination()))
        }

    }

    private fun getTabsDestination(): Int {
        App.outLogs("getTabsDestination")
        return R.id.tabsFragment
    }
}