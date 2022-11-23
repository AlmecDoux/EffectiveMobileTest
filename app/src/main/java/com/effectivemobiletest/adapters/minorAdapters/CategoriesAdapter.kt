package com.effectivemobiletest.adapters.minorAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.test.databinding.BlockCategoryItemLayoutBinding

class CategoriesAdapter(private var categoryItems:List<CategoryItem>):
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(){

    private var selectedPosition = 0

    inner class CategoryViewHolder(
        private val binding: BlockCategoryItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(categoryItem: CategoryItem, selected:Boolean){
            categoryItem.img?.let { imageResource->
                binding.categoryImg.setImageResource(imageResource)
            }
            binding.categoryImg.isSelected = selected
            binding.categoryName.isSelected = selected
            binding.categoryName.text = categoryItem.title
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = BlockCategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItems[position], selected = (selectedPosition == position))
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

}