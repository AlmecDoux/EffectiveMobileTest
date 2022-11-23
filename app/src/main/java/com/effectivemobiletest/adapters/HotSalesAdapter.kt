package com.effectivemobiletest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.effectivemobile.test.databinding.BlockHotSaleItemLayoutBinding
import com.squareup.picasso.Picasso

class HotSalesAdapter(private var hotSalesItems:List<HotSalesItem>):
    RecyclerView.Adapter<HotSalesAdapter.HotSalesViewHolder>(){

    inner class HotSalesViewHolder(
        private val binding: BlockHotSaleItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(hotSaleItem:HotSalesItem){
            binding.titleHotSaleItem.text = hotSaleItem.title
            binding.subTitleHotSaleItem.text = hotSaleItem.subTitle
            binding.iconNew.visibility = if(hotSaleItem.isNew) View.VISIBLE else View.GONE
            Picasso.get().load(hotSaleItem.pictureURL).into(binding.imgHotSaleItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesViewHolder {
        val binding = BlockHotSaleItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return HotSalesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
        val pos = adjustedPosition(position)
        holder.bind(hotSalesItems[pos])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
    private fun adjustedPosition(position: Int): Int {
        return position % hotSalesItems.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(adjustedPosition(position))
    }
}