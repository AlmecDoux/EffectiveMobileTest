package com.effectivemobiletest.adapters.adaptersData

import com.effectivemobiletest.adapters.DisplayableItem

data class HeaderTitleAdapterItem(
    val headerTitle:String = "",
    val linkText:String = "",
    val clickLink:()->Unit
): DisplayableItem
