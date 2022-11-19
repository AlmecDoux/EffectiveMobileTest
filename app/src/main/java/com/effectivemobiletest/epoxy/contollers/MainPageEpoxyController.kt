package com.effectivemobiletest.epoxy.contollers

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.airbnb.epoxy.carousel
import com.effectivemobiletest.decorations.marginDecorations.CenterZoomLinearLayoutManager
import com.effectivemobiletest.epoxy.contollers.customEpoxyViews.NonSnappingHorizontalCarouselModel_
import com.effectivemobiletest.epoxy.models.BestSellerModel
import com.effectivemobiletest.epoxy.models.CategoryModel
import com.effectivemobiletest.epoxy.models.HeaderTitleModel
import com.effectivemobiletest.epoxy.models.HotSalesModel
import com.effectivemobiletest.epoxy.models.mapperClasses.*

class MainPageEpoxyController(): TypedEpoxyController<List<EpoxyData>>() {

    override fun buildModels(data: List<EpoxyData>) {
        data.forEach { cellData ->
            when(cellData) {
                is EpoxyHotSalesItem -> addHotSalesItems(cellData)
                is EpoxyHeaderTitleItem -> addHeaderTitleItem(cellData)
                is EpoxyCategoryItems -> addCategoriesItems(cellData)
                is EpoxyBestSellerItem -> addBestSellerItems(cellData)
            }
        }
    }

    private fun addBestSellerItems(bestSellerItem: EpoxyBestSellerItem) {
        if (bestSellerItem.bestSellerItems.isEmpty()) return
        val itemsHotSale = bestSellerItem.bestSellerItems
        val items = itemsHotSale.map {
            BestSellerModel(it, bestSellerItem.clickFavorite, bestSellerItem.clickOn)
                .id(it.id)
        }
        carousel {
            id("carousel")
            models(items)
            hasFixedSize(true)
            padding(Carousel.Padding(10,0,10,0,10))
            numViewsToShowOnScreen(2.0f)
        }
    }

    private fun addCategoriesItems(categoryItems: EpoxyCategoryItems) {
        if (categoryItems.categoryItem.isEmpty()) return
        val itemsHotSale = categoryItems.categoryItem
        val items = itemsHotSale.map {
            CategoryModel(it, categoryItems.clickOn)
                .id(it.id)
        }


        NonSnappingHorizontalCarouselModel_()
            .id("carousel")
            .models(items)
            .hasFixedSize(true)
            .addTo(this)

    }

    private fun addHotSalesItems(epoxyHotSalesItem: EpoxyHotSalesItem){
        if (epoxyHotSalesItem.hotSalesItems.isEmpty()) return
        val itemsHotSale = epoxyHotSalesItem.hotSalesItems
        val items = itemsHotSale.map {
            HotSalesModel(it)
                .id(it.id)
        }
        carousel {
            id("carousel")
            models(items)
            hasFixedSize(true)
            padding(Carousel.Padding(10,0,10,0,10))
            numViewsToShowOnScreen(1.0f)
            onBind { _, carousel, id ->
                carousel.layoutManager = CenterZoomLinearLayoutManager(carousel.context)
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
            .id("header")
            .addTo(this)

    }

}