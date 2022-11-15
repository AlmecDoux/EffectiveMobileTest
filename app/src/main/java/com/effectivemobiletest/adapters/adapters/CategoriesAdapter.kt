package com.effectivemobiletest.adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.databinding.CategoryItemLayoutBinding
import com.effectivemobiletest.adapters.adaptersModels.CategoriesAdapterModel
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapter
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem

class CategoriesAdapter(private val clickCategory: ((idCategory: Int) -> Unit)? = null):
    DelegateAdapter<CategoriesAdapterModel, CategoriesAdapter.CategoriesViewHolder>(CategoriesAdapterModel::class.java) {

    inner class CategoriesViewHolder(
        private val context: Context,
        private val binding: CategoryItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoriesAdapterModel) {
            val categoryItem = item.content()
            with(binding){
                categoryItem.img?.let{
                   categoryImg.setImageDrawable(ContextCompat.getDrawable(context, it))
                }
                categoryName.text = categoryItem.title
                root.setOnClickListener{
                    clickCategory?.invoke(categoryItem.id)
                }
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(parent.context, binding)
    }

    override fun bindViewHolder(model: CategoriesAdapterModel,
                                viewHolder: CategoriesViewHolder,
                                payloads: List<DelegateAdapterItem.Payloadable>) {
        viewHolder.bind(model)
    }
}