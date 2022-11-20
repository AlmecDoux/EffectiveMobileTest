package com.effectivemobiletest.epoxy.models.mapperClasses

data class EpoxyHeaderTitleItem(
    val headerTitle:String = "",
    val linkText:String = "",
    val clickLink:()->Unit
):EpoxyData()
