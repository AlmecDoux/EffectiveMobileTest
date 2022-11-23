package com.effectivemobiletest.adapters

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View.OVER_SCROLL_NEVER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.databinding.CarouselLayoutBinding
import com.effectivemobile.test.databinding.ColorProductLayoutBinding
import com.effectivemobile.test.databinding.HeaderTitleLayoutBinding
import com.effectivemobile.test.databinding.LocationItemLayoutBinding
import com.effectivemobile.test.databinding.SearchProductLayoutBinding
import com.effectivemobiletest.adapters.adaptersData.*
import com.effectivemobiletest.decorations.layoutManagers.CenterZoomLinearLayoutManager
import com.effectivemobiletest.decorations.marginDecorations.MarginItemDecoration
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

interface DisplayableItem

fun headerTitleAdapterDelegate() = adapterDelegateViewBinding<HeaderTitleAdapterItem, DisplayableItem, HeaderTitleLayoutBinding>(
    { layoutInflater, root -> HeaderTitleLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        binding.headerTitle.text = item.headerTitle
        binding.headerLink.text = item.linkText
        binding.headerLink.setOnClickListener{
            item.clickLink.invoke()
        }
    }
}

fun locationToolsAdapterDelegate() = adapterDelegateViewBinding<LocationAdapterItem, DisplayableItem, LocationItemLayoutBinding>(
    { layoutInflater, root -> LocationItemLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        binding.locationsSpinner.text = item.defaultLocation
        binding.btnFilter.setOnClickListener {
            item.clickOnFilter.invoke()
        }
    }
}
fun categoriesAdapterDelegate() = adapterDelegateViewBinding<CategoryAdapterItems, DisplayableItem, CarouselLayoutBinding>(
    { layoutInflater, root -> CarouselLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        binding.photosCarousel.adapter = CategoriesAdapter(item.categoryItems)
        binding.photosCarousel.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
        binding.photosCarousel.overScrollMode = OVER_SCROLL_NEVER
    }
}

fun carouselHotSaleAdapterDelegate() = adapterDelegateViewBinding<HotSalesAdapterItem, DisplayableItem, CarouselLayoutBinding>(
    { layoutInflater, root -> CarouselLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        val snapHelper = LinearSnapHelper()
        binding.photosCarousel.adapter = HotSalesAdapter(hotSalesItems = item.hotSalesItems)
        binding.photosCarousel.layoutManager = CenterZoomLinearLayoutManager(itemView.context)
        binding.photosCarousel.overScrollMode = OVER_SCROLL_NEVER
        snapHelper.attachToRecyclerView(binding.photosCarousel)
        binding.photosCarousel.layoutManager?.scrollToPosition(Integer.MAX_VALUE / 2)
    }
}

fun searchProductAdapterDelegate() = adapterDelegateViewBinding<SearchAdapterItem, DisplayableItem, SearchProductLayoutBinding>(
    { layoutInflater, root -> SearchProductLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    item.search.invoke(it.toString())
                }
            }
        })
        binding.qrSearchBtn.setOnClickListener {
            item.qrBtnClick.invoke()
        }
    }
}

fun bestSalesAdapterDelegate() = adapterDelegateViewBinding<BestSalesAdapterItem, DisplayableItem, CarouselLayoutBinding>(
{ layoutInflater, root -> CarouselLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        binding.photosCarousel.adapter = BestSalesAdapter(item)
        binding.photosCarousel.layoutManager = GridLayoutManager(itemView.context, 2, GridLayoutManager.VERTICAL, false)
        binding.photosCarousel.overScrollMode = OVER_SCROLL_NEVER
        binding.photosCarousel.addItemDecoration(MarginItemDecoration(25))
    }
}

fun productColorsAdapterDelegate() = adapterDelegateViewBinding<ProductColorsAdapterItem, DisplayableItem, CarouselLayoutBinding>(
    { layoutInflater, root -> CarouselLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        val layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        binding.photosCarousel.layoutParams = layoutParams
        binding.photosCarousel.adapter = ColorsProductAdapter(item.listOfColors)
        binding.photosCarousel.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.photosCarousel.overScrollMode = OVER_SCROLL_NEVER
        binding.photosCarousel.addItemDecoration(MarginItemDecoration(25))
    }
}

fun productCapacityAdapterDelegate() = adapterDelegateViewBinding<ProductCapacityAdapterItem, DisplayableItem, CarouselLayoutBinding>(
    { layoutInflater, root -> CarouselLayoutBinding.inflate(layoutInflater, root, false) }
){
    bind {
        val layoutParams = LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER
        layoutParams.marginStart = 10
        binding.photosCarousel.layoutParams = layoutParams
        binding.photosCarousel.adapter = CapacityProductAdapter(item.listOfCapacities)
        binding.photosCarousel.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.photosCarousel.overScrollMode = OVER_SCROLL_NEVER
        binding.photosCarousel.addItemDecoration(MarginItemDecoration(25))
    }
}
