package com.effectivemobiletest.epoxy.models.mapperClasses

class EpoxyProductCapacityItem(
    val listOfCapacities:List<Capacity>,
):EpoxyData()

data class Capacity(
    val capacityValue:String,
    var isSelected:Boolean
)