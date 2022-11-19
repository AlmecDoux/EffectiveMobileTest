package com.effectivemobiletest.epoxy.models

import android.text.Editable
import android.text.TextWatcher
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.SearchProductLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxySearchItem

class SearchItemModel(
    private val epoxySearchItem: EpoxySearchItem
): ViewBindingKotlinModel<SearchProductLayoutBinding>(R.layout.search_product_layout) {

    override fun SearchProductLayoutBinding.bind() {
        searchField.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    epoxySearchItem.search.invoke(it.toString())
                }
            }
        })
        qrSearchBtn.setOnClickListener {
            epoxySearchItem.qrBtnClick.invoke()
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}