package com.effectivemobiletest.adapters.minorAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.SpinnerItemLayoutBinding

class SpinnerAdapter(
    context: Context,
    var data: List<String>,
): ArrayAdapter<String>(context, 0, data){

    private var layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: layoutInflater.inflate(R.layout.spinner_header_layout, parent, false)
    }

    override fun isEnabled(position: Int) = position != 0

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SpinnerItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        val item = getItem(position)
        item?.let {
            binding.textSpinner.text = item
        }
        return binding.root
    }

}