package com.effectivemobiletest.ui.pages.productsPage

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.epoxy.contollers.MainPageEpoxyController
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsPageFragment: BaseFragment<ProductsPageLayoutBinding, ProductsViewModel>() {
    override val viewModel: ProductsViewModel by viewModels()
    override val binding: ProductsPageLayoutBinding by viewBinding(CreateMethod.INFLATE)

    private val controller by lazy { MainPageEpoxyController() }

    override fun setUpViewsBinding() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.epoxyRecyclerView.layoutManager = layoutManager
        binding.epoxyRecyclerView.setController(controller)
        binding.navigationBar.setupWithNavController(findNavController())
    }
    override fun observeData() {
        lifecycle.addObserver(viewModel)
        viewModel.mainPageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { data->
                controller.setData(data)
            }
        }
    }
}