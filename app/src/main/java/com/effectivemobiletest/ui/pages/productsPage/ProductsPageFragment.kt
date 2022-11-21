package com.effectivemobiletest.ui.pages.productsPage

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.domain.annotations.IoDispatcher
import com.effectivemobile.domain.annotations.MainDispatcher
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.epoxy.contollers.MainPageEpoxyController
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductsPageFragment: BaseFragment<ProductsPageLayoutBinding, ProductsViewModel>() {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: ProductsViewModel by viewModels()
    override val binding: ProductsPageLayoutBinding by viewBinding(CreateMethod.INFLATE)

    private val controller by lazy { MainPageEpoxyController() }

    override fun setUpViewsBinding() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.epoxyRecyclerView.apply {
            set3DItem(false)
            setAlpha(true)
            setInfinite(true)
        }
        binding.navigationBar.setupWithNavController(findNavController())
    }
    override fun observeData() {
        lifecycle.addObserver(viewModel)
        viewModel.mainPageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { data->
                lifecycleScope.launch(mainDispatcher){
                    controller.setData(data)
                }
            }
        }
    }
}