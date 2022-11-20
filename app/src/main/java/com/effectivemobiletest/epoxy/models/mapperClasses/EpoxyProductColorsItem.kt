package com.effectivemobiletest.epoxy.models.mapperClasses

import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager

data class EpoxyProductColorsItem(
    val listOfColors:List<ColorsOfPoint>,
):EpoxyData()

data class ColorsOfPoint(
    val color:String,
    var isSelected:Boolean
)