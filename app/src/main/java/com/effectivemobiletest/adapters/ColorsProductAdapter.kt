package com.effectivemobiletest.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.databinding.ColorProductLayoutBinding

class ColorsProductAdapter(private var colors:List<String>):
    RecyclerView.Adapter<ColorsProductAdapter.ColorViewHolder>(){

    private var selectedPosition = 0

    inner class ColorViewHolder(
        private val binding: ColorProductLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(color: String, selected:Boolean){
            val colorResource = Color.parseColor(color)
            binding.selectedFlag.visibility = if(selected) View.VISIBLE else View.GONE
            binding.colorField.backgroundTintList = ColorStateList.valueOf(colorResource)
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ColorProductLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position], selected = (selectedPosition == position))
    }

    override fun getItemCount(): Int {
        return colors.size
    }

}