package com.effectivemobiletest.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class FilterAdapter:ListDelegationAdapter<List<DisplayableItem>>(
    stickyAdapterDelegate(),
    spinnerAdapterDelegate()
)