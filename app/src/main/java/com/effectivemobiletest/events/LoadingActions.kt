package com.effectivemobiletest.events

sealed class LoadingActions{
    object ShowLoading:LoadingActions()
    object HideLoading:LoadingActions()
}
