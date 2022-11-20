package com.effectivemobiletest.extensions

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.NavigationActions

fun MutableLiveData<Event<NavigationActions>>.navigate(directions: NavDirections){
    post(Event(NavigationActions.NavigationDirections(direction = directions)))

}

fun MutableLiveData<Event<NavigationActions>>.navigateBack(){
    post(Event(NavigationActions.NavigationBack))
}