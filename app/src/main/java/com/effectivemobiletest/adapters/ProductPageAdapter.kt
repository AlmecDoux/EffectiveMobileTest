package com.effectivemobiletest.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ProductPageAdapter: ListDelegationAdapter<List<DisplayableItem>>(
    locationToolsAdapterDelegate(),
    headerTitleAdapterDelegate(),
    categoriesAdapterDelegate(),
    searchProductAdapterDelegate(),
    carouselHotSaleAdapterDelegate(),
    bestSalesAdapterDelegate()
)
