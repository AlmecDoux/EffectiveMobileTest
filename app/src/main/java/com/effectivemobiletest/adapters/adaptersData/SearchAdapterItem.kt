package com.effectivemobiletest.adapters.adaptersData

import com.effectivemobiletest.adapters.DisplayableItem

data class SearchAdapterItem(
    val search:(searchText:String)->Unit,
    val qrBtnClick:()->Unit
):DisplayableItem