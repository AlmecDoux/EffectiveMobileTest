package com.effectivemobiletest.epoxy.models.mapperClasses

data class EpoxyLocationItem(
    val listLocations:List<String>,
    val clickOnFilter:()->Unit,
    val chooseLocation:()->Unit
):EpoxyData()