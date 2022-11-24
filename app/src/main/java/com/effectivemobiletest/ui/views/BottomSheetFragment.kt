package com.effectivemobiletest.ui.views

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.FiltersBottomLayoutBinding
import com.effectivemobiletest.adapters.DisplayableItem
import com.effectivemobiletest.adapters.FilterAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment(private val data:List<DisplayableItem>) : BottomSheetDialogFragment() {

    private lateinit var binding: FiltersBottomLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FiltersBottomLayoutBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.doneBtn.setOnClickListener{
            this.dismiss()
        }
        binding.btnBack.setOnClickListener{
            this.dismiss()
        }
        this.isCancelable = true
        val adapter = FilterAdapter().apply {
            items = data
        }
        binding.filtersRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.filtersRecycler.itemAnimator = DefaultItemAnimator()
        binding.filtersRecycler.adapter = adapter
    }
    fun reCreateBottomSheetFragment():BottomSheetFragment {
        return BottomSheetFragment(data)
    }

    override fun onResume() {
        super.onResume()
        dialog?.let {
            it.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
            it.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            it.window?.setDimAmount(0.5f)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
            it.window?.setBackgroundDrawable(ColorDrawable(0))
        }
    }
    companion object{
        val TAG = "bottom_sheet"
    }
}