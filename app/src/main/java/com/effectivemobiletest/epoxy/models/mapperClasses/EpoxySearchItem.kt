package com.effectivemobiletest.epoxy.models.mapperClasses

data class EpoxySearchItem(
    val search:(searchText:String)->Unit,
    val qrBtnClick:()->Unit
):EpoxyData()