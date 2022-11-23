package com.effectivemobiletest.adapters.minorAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.CapacityItemLayoutBinding

class CapacityProductAdapter(private var capacity:List<String>):
    RecyclerView.Adapter<CapacityProductAdapter.CapacityViewHolder>(){

    private var selectedPosition = 0

    inner class CapacityViewHolder(
        private val binding: CapacityItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(capacityValue: String, selected:Boolean){
            if(selected){
                binding.card.background = ContextCompat.getDrawable(itemView.context, R.drawable.primary_rounded_button)
                binding.capacityField.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
            else{
                binding.card.background = null
                binding.capacityField.setTextColor(ContextCompat.getColor(itemView.context, R.color.grey))
            }
            binding.capacityField.text = capacityValue
            binding.card.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapacityViewHolder {
        val binding = CapacityItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return CapacityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CapacityViewHolder, position: Int) {
        holder.bind(capacity[position], selected = (selectedPosition == position))
    }

    override fun getItemCount(): Int {
        return capacity.size
    }

}