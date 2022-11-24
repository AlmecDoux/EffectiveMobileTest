package com.effectivemobiletest.decorations

import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilterGroup

fun GPUImageFilterGroup(): GPUImageFilterGroup {
    val filterGroup = GPUImageFilterGroup()
    val filter = GPUImageBrightnessFilter()
    filter.setBrightness(-0.3f)
    filterGroup.addFilter(filter)
    return filterGroup
}
