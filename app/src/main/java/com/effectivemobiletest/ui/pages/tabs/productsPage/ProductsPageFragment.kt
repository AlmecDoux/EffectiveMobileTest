package com.effectivemobiletest.ui.pages.tabs.productsPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.adapters.adapters.CategoriesAdapter
import com.effectivemobiletest.adapters.adapters.HotSalesAdapter
import com.effectivemobiletest.adapters.delegateAdapter.CompositeAdapter
import com.effectivemobiletest.decorations.marginDecorations.HorizontalMarginItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsPageFragment:Fragment() {

    private val categoryAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CategoriesAdapter{})
            .build()
    }
    private val hotSaleAdapter by lazy {
        CompositeAdapter.Builder()
            .add(HotSalesAdapter{})
            .build()
    }

    private val binding:ProductsPageLayoutBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel:ProductsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesLM = LinearLayoutManager(requireContext())
        categoriesLM.orientation = LinearLayoutManager.HORIZONTAL
        lifecycle.addObserver(viewModel)
        binding.categoriesRV.layoutManager = categoriesLM
        binding.categoriesRV.adapter = categoryAdapter
        binding.hotSalesRV.adapter = hotSaleAdapter
        binding.hotSalesRV.apply {
            setAlpha(true)
            setInfinite(true)
            setIntervalRatio(0.8f)
        }
        binding.hotSalesRV.addItemDecoration(HorizontalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.small_margin)))
        viewModel.hotSalesData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { listOfElement->
                hotSaleAdapter.submitList(listOfElement)
            }
        }
        viewModel.categoryData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { listOfElement->
                categoryAdapter.submitList(listOfElement)
            }
        }
    }
}