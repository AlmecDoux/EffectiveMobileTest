package com.effectivemobiletest.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ProductFeatureAdapter: ListDelegationAdapter<List<DisplayableItem>>(
    productColorsAdapterDelegate(),
    productCapacityAdapterDelegate()
)