package com.effectivemobiletest.adapters.minorAdapters

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.SpinnerItemLayoutBinding

class SpinnerAdapter(
    context: Context,
    var data: List<String>,
): ArrayAdapter<String>(context, 0, data){

    private var layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.spinner_header_layout, parent, false)
        view.findViewById<TextView>(R.id.textSpinner).text = getItem(position)
        return view
    }

    override fun isEnabled(position: Int) = position != 0

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val item = getItem(position)
        if (position == 0) {
            view = layoutInflater.inflate(R.layout.spinner_header_layout, parent, false)
            view.findViewById<ImageView>(R.id.arrowSpinner)?.animate()?.rotation(180f)?.setDuration(400)
                ?.start()
            view.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
        } else {
            view = layoutInflater.inflate(R.layout.spinner_item_layout, parent, false)

        }
        item?.let {
            setItemSpinner(view, item)
        }

        return view
    }
    private fun setItemSpinner(view: View, item: String) {
        view.findViewById<TextView>(R.id.textSpinner).text = item
    }
}