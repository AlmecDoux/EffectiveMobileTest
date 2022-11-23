package com.effectivemobiletest.adapters.adaptersData

import com.effectivemobiletest.adapters.DisplayableItem

data class LocationAdapterItem(
    val defaultLocation:String,
    val clickOnFilter:()->Unit,
    val chooseLocation:()->Unit
): DisplayableItem