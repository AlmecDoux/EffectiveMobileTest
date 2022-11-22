package com.effectivemobiletest.epoxy.models.mapperClasses

class EpoxyProductCapacityItem(
    val listOfCapacities:List<Capacity>,
):EpoxyData()

data class Capacity(
    val capacityValue:String,
    var isSelected:Boolean
)


fun List<String>.mapToCapacityItem():EpoxyProductCapacityItem{
    return EpoxyProductCapacityItem(
        listOfCapacities = this.mapIndexed { index, capacityValue ->
            if(index == 0) return@mapIndexed Capacity(capacityValue = capacityValue, isSelected = true)
            else return@mapIndexed Capacity(capacityValue = capacityValue, isSelected = false)
        }
    )
}