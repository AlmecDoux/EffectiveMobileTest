package com.effectivemobiletest.epoxy.models.mapperClasses

data class EpoxyHeaderTitleItem(
    val headerTitle:String = "",
    val clickLink:()->Unit
):EpoxyData()
