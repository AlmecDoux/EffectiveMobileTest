package com.effectivemobiletest.epoxy.models

import androidx.core.content.ContextCompat
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.CapacityItemLayoutBinding
import com.effectivemobiletest.epoxy.ViewBindingKotlinModel
import com.effectivemobiletest.epoxy.models.mapperClasses.Capacity

class CapacityModel(
    private val capacity: Capacity,
    private val listenerOfSelect:((selected:Boolean)->Unit)
): ViewBindingKotlinModel<CapacityItemLayoutBinding>(R.layout.capacity_item_layout) {


    override fun CapacityItemLayoutBinding.bind() {
        val selected = capacity.isSelected
        if(selected){
            root.background = ContextCompat.getDrawable(root.context, R.drawable.primary_rounded_button)
            capacityField.setTextColor(ContextCompat.getColor(root.context, R.color.white))
        }
        else{
            root.background = null
            capacityField.setTextColor(ContextCompat.getColor(root.context, R.color.grey))
        }
        capacityField.text = capacity.capacityValue
        root.setOnClickListener {
            listenerOfSelect.invoke(capacity.isSelected)
        }
    }
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}
