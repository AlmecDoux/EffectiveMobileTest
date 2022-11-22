package com.effectivemobiletest.epoxy.models.mapperClasses

data class EpoxyProductColorsItem(
    val listOfColors:List<ColorsOfPoint>,
):EpoxyData()

data class ColorsOfPoint(
    val color:String,
    var isSelected:Boolean
)

fun List<String>.mapToColorOfPoint():EpoxyProductColorsItem{
    return EpoxyProductColorsItem(
        listOfColors = this.mapIndexed { index, color ->
            if(index == 0) return@mapIndexed ColorsOfPoint(color = color, isSelected = true)
            else return@mapIndexed ColorsOfPoint(color = color, isSelected = false)
        }
    )
}