package com.effectivemobiletest.events

import androidx.navigation.NavDirections

sealed class NavigationActions{
    class NavigationDirections(val direction: NavDirections):NavigationActions()
    object NavigationBack : NavigationActions()
}