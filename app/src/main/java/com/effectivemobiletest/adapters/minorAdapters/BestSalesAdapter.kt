package com.effectivemobiletest.adapters.minorAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.domain.models.subtypes.BestSalesItem
import com.effectivemobile.test.databinding.BlockBestSellerItemLayoutBinding
import com.effectivemobiletest.adapters.adaptersData.BestSalesAdapterItem
import com.effectivemobiletest.utils.AmountDecorator
import com.squareup.picasso.Picasso

class BestSalesAdapter(
    private val bestSalesItems: BestSalesAdapterItem
):
    RecyclerView.Adapter<BestSalesAdapter.BestSalesViewHolder>(){

    inner class BestSalesViewHolder(
        private val binding: BlockBestSellerItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(bestSalesItem: BestSalesItem, itemPosition:Int){
            binding.favoriteBestSellerItem.isSelected = bestSalesItem.isFavorites
            binding.favoriteBestSellerItem.setOnClickListener{
                val isSelected = !binding.favoriteBestSellerItem.isSelected
                bestSalesItems.items[itemPosition].isFavorites = isSelected
                notifyItemChanged(itemPosition)
            }
            Picasso.get().load(bestSalesItem.pictureURL).into(binding.imgBestSellerItem)
            val withOutPriceAmount = AmountDecorator.getNormalAmountToStringWithCurrency(bestSalesItem.priceWithoutDiscount)
            val priceAmount = AmountDecorator.getNormalAmountToStringWithCurrency(bestSalesItem.discountPrice)
            binding.discountPrice.text = priceAmount
            binding.priceWithOutDiscount.text = HtmlCompat.fromHtml("<s>$withOutPriceAmount</s>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.productTitle.text = bestSalesItem.title
            binding.root.setOnClickListener {
                bestSalesItems.clickOn.invoke(bestSalesItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSalesViewHolder {
        val binding = BlockBestSellerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return BestSalesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestSalesViewHolder, position: Int) {
        holder.bind(bestSalesItems.items[position], position)
    }

    override fun getItemCount(): Int {
        return bestSalesItems.items.size
    }

}