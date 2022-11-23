package com.effectivemobiletest.epoxy.models

import androidx.core.content.ContextCompat
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.CapacityItemLayoutBinding

//class CapacityModel(
//    private val capacity: Capacity,
//    private val listenerOfSelect:((selected:Boolean)->Unit)
//): ViewBindingKotlinModel<CapacityItemLayoutBinding>(R.layout.capacity_item_layout) {
//
//
//    override fun CapacityItemLayoutBinding.bind() {
//        val selected = capacity.isSelected
//        if(selected){
//            card.background = ContextCompat.getDrawable(card.context, R.drawable.primary_rounded_button)
//            capacityField.setTextColor(ContextCompat.getColor(card.context, R.color.white))
//        }
//        else{
//            card.background = null
//            capacityField.setTextColor(ContextCompat.getColor(card.context, R.color.grey))
//        }
//        capacityField.text = capacity.capacityValue
//        card.setOnClickListener {
//            listenerOfSelect.invoke(capacity.isSelected)
//        }
//    }
//    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
//        return totalSpanCount
//    }
//
//}
