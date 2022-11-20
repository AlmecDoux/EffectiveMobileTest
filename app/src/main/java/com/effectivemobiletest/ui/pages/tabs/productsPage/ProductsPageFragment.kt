package com.effectivemobiletest.ui.pages.tabs.productsPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.epoxy.contollers.MainPageEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsPageFragment:Fragment() {
    private val viewModel: ProductsViewModel by viewModels()
    private val binding: ProductsPageLayoutBinding by viewBinding(CreateMethod.INFLATE)
    private val controller by lazy { MainPageEpoxyController() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        lifecycle.addObserver(viewModel)
        binding.epoxyRecyclerView.layoutManager = layoutManager
        binding.epoxyRecyclerView.setController(controller)
        viewModel.mainPageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { data->
                controller.setData(data)
            }
        }
        binding.navigationBar.setupWithNavController(findNavController())
    }
}