package com.effectivemobiletest.epoxy.contollers

import android.view.View
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.epoxy.contollers.customEpoxyViews.nonSnappingHorizontalCarousel
import com.effectivemobiletest.epoxy.contollers.customEpoxyViews.nonSnappingHorizontalShortCarousel
import com.effectivemobiletest.epoxy.models.CapacityModel
import com.effectivemobiletest.epoxy.models.CategoryModel
import com.effectivemobiletest.epoxy.models.ColorsProductModel
import com.effectivemobiletest.epoxy.models.mapperClasses.*

class ProductCharacteristicsController:TypedEpoxyController<List<EpoxyData>>() {

    override fun buildModels(data: List<EpoxyData>) {
        data.forEach { cellData ->
            when(cellData) {
                is EpoxyProductColorsItem -> {
                    addProductColorsItem(data.indexOf(cellData), cellData)
                }
                is EpoxyProductCapacityItem -> {
                    addProductCapacityItem(data.indexOf(cellData), cellData)
                }

            }
        }
    }

    private fun addProductColorsItem(indexOfModel:Int, cellData: EpoxyProductColorsItem) {
        if (cellData.listOfColors.isEmpty()) return
        val itemsColors = cellData.listOfColors
        val items = itemsColors.mapIndexed { id, itemColors->
            ColorsProductModel(itemColors) {
                if(it) return@ColorsProductModel
                cellData.listOfColors.forEachIndexed { index, colorsOfPoint ->
                    if(index == id){
                        colorsOfPoint.isSelected = true
                    }
                    else{
                        if(colorsOfPoint.isSelected){
                            colorsOfPoint.isSelected = false
                        }
                    }
                }
                this.notifyModelChanged(indexOfModel)
            }.id(itemColors.color)

        }
        nonSnappingHorizontalShortCarousel {
            id("carouselColors")
            models(items)
            hasFixedSize(true)
            //padding(Carousel.Padding(0,30,0,30,25))
            onBind {_, carousel, _ -> carousel.overScrollMode = View.OVER_SCROLL_NEVER}
        }
    }

    private fun addProductCapacityItem(indexOfModel: Int, cellData: EpoxyProductCapacityItem) {
        if (cellData.listOfCapacities.isEmpty()) return
        val itemsColors = cellData.listOfCapacities
        val items = itemsColors.mapIndexed { id, itemColors->
            CapacityModel(itemColors) {
                if(it) return@CapacityModel
                cellData.listOfCapacities.forEachIndexed { index, colorsOfPoint ->
                    if(index == id){
                        colorsOfPoint.isSelected = true
                    }
                    else{
                        if(colorsOfPoint.isSelected){
                            colorsOfPoint.isSelected = false
                        }
                    }
                }
                this.notifyModelChanged(indexOfModel)
            }.id(itemColors.capacityValue)

        }
        nonSnappingHorizontalShortCarousel {
            id("carouselCapacity")
            models(items)
            hasFixedSize(true)
            //padding(Carousel.Padding(150,50,0,50,25))
            onBind {model, carousel, _ ->
                carousel.overScrollMode = View.OVER_SCROLL_NEVER
            }
        }
    }
}