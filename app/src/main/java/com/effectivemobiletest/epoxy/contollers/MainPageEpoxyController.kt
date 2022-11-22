package com.effectivemobiletest.epoxy.contollers

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.effectivemobile.test.R
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.decorations.marginDecorations.CenterZoomLinearLayoutManager
import com.effectivemobiletest.epoxy.contollers.customEpoxyViews.*
import com.effectivemobiletest.epoxy.models.*
import com.effectivemobiletest.epoxy.models.mapperClasses.*

class MainPageEpoxyController : TypedEpoxyController<List<EpoxyData>>() {


    override fun buildModels(data: List<EpoxyData>) {
        outLogs("Build model")
        data.forEach { cellData ->
            when(cellData) {
                is EpoxyHotSalesItem -> addHotSalesItems(cellData)
                is EpoxyHeaderTitleItem -> addHeaderTitleItem(cellData)
                is EpoxyCategoryItems -> addCategoriesItems(data.indexOf(cellData), cellData)
                is EpoxyBestSellerItem -> addBestSellerItems(cellData)
                is EpoxyLocationItem -> addLocationItem(cellData)
                is EpoxySearchItem -> addSearchItem(cellData)
            }
        }
    }

    private fun addSearchItem(epoxySearchItem: EpoxySearchItem) {
        SearchItemModel(
            epoxySearchItem = epoxySearchItem
        ).id("search")
            .addTo(this)

    }

    private fun addLocationItem(locationItem: EpoxyLocationItem) {
        EpoxyLocationModel(
            locationItem.listLocations[0], locationItem.clickOnFilter, locationItem.chooseLocation)
            .id("locationItem")
            .addTo(this)
    }

    private fun addBestSellerItems(bestSellerItem: EpoxyBestSellerItem) {
        if (bestSellerItem.bestSellerItems.isEmpty()) return
        val itemsHotSale = bestSellerItem.bestSellerItems
        val items = itemsHotSale.map {
            BestSellerModel(it, bestSellerItem.clickFavorite, bestSellerItem.clickOn)
                .id(it.id)
        }
        gridVerticalEpoxyView {
            id("bestSeller")
            models(items)
            hasFixedSize(true)
            paddingRes(R.dimen.medium_margin)
            spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount}
        }
    }

    private fun addCategoriesItems(indexOfModel:Int, categoryItems: EpoxyCategoryItems) {
        if (categoryItems.categoryItems.isEmpty()) return
        val itemsCategories = categoryItems.categoryItems
        val items = itemsCategories.mapIndexed { id, itemCategory->
            CategoryModel(itemCategory, listenerOfSelect = { selected, idOfCategory->
                if(selected) return@CategoryModel
                categoryItems.categoryItems.forEachIndexed { index, category ->
                    if(index == id){
                        category.isSelected = true
                    }
                    else{
                        if(category.isSelected){
                            category.isSelected = false
                        }
                    }
                }
                categoryItems.clickOn(idOfCategory)
                this.notifyModelChanged(indexOfModel)
            }).id(itemCategory.categoryItem.id)
        }
        nonSnappingHorizontalCarousel {
            id("carouselCategories")
            models(items)
            hasFixedSize(true)
            onBind {_, carousel, _ -> carousel.overScrollMode = View.OVER_SCROLL_NEVER}
        }

    }

    private fun addHotSalesItems(epoxyHotSalesItem: EpoxyHotSalesItem){
        if (epoxyHotSalesItem.hotSalesItems.isEmpty()) return
        val itemsHotSale = epoxyHotSalesItem.hotSalesItems
        val items = itemsHotSale.map {
            HotSalesModel(it)
                .id(it.id)
        }
        carousel {
            id("carouselHotSalesItems")
            models(items)
            hasFixedSize(true)
            paddingDp(10)
            numViewsToShowOnScreen(1.0f)
            onBind { _, carousel, id ->
                carousel.layoutManager = CenterZoomLinearLayoutManager(carousel.context)
                carousel.overScrollMode = View.OVER_SCROLL_NEVER
                val snapHelper = LinearSnapHelper()
                carousel.scrollToPosition(1)
                carousel.post {
                    val viewByPosition = (carousel.layoutManager as RecyclerView.LayoutManager ).findViewByPosition(1)
                    viewByPosition?.let {
                        val snapDistance = snapHelper.calculateDistanceToFinalSnap((carousel.layoutManager as RecyclerView.LayoutManager ), it)
                        if (snapDistance?.get(0) != 0 || snapDistance[1] != 0) {
                            carousel.scrollBy(snapDistance?.get(0)!!,
                                snapDistance[1]
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addHeaderTitleItem(epoxyHeaderTitleItem: EpoxyHeaderTitleItem){
        HeaderTitleModel(epoxyHeaderTitleItem)
            .id("headerTitle")
            .addTo(this)

    }
}