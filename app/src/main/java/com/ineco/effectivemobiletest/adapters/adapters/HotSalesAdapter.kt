package com.ineco.effectivemobiletest.adapters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.domain.models.subtypes.HotSalesItem
import com.ineco.effectivemobiletest.adapters.adaptersModels.HotSalesAdapterModel
import com.ineco.effectivemobiletest.adapters.delegateAdapter.DelegateAdapter
import com.ineco.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem
import com.ineco.effectivemobiletest.databinding.HotSaleItemLayoutBinding
import com.squareup.picasso.Picasso

class HotSalesAdapter(private val buyBtn: (hotSaleItem:HotSalesItem) -> Unit):
    DelegateAdapter<HotSalesAdapterModel, HotSalesAdapter.HotSalesViewHolder>(HotSalesAdapterModel::class.java) {


    inner class HotSalesViewHolder(
        private val context: Context,
        private val binding: HotSaleItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HotSalesAdapterModel) {
            val hotSaleItem = item.content()
            with(binding){
                titleHotSaleItem.text = hotSaleItem.title
                iconNew.visibility = if(hotSaleItem.isNew) View.VISIBLE else View.GONE
                subTitleHotSaleItem.text = hotSaleItem.subTitle
                btnBuyNow.setOnClickListener{ buyBtn.invoke(hotSaleItem) }
                Picasso.with(context).load(hotSaleItem.pictureURL).into(imgHotSaleItem)
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HotSalesViewHolder(parent.context, HotSaleItemLayoutBinding.bind(parent))
    }

    override fun bindViewHolder(model: HotSalesAdapterModel, viewHolder: HotSalesViewHolder,
                                payloads: List<DelegateAdapterItem.Payloadable>) {
        viewHolder.bind(model)
    }
}