package com.effectivemobiletest.ui.pages.tabs.productsPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.adapters.adapters.HotSalesAdapter
import com.effectivemobiletest.adapters.delegateAdapter.CompositeAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class ProductsPageFragment:Fragment() {

    private val compositeAdapter by lazy {
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
        outLogs("Created ProductsPageFragment")
        binding.homePageRV.adapter = compositeAdapter
        viewModel.pageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { listOfElement->
                compositeAdapter.submitList(listOfElement)
            }
        }
    }
}