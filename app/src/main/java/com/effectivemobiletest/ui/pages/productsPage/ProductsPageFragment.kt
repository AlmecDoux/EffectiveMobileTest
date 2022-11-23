package com.effectivemobiletest.ui.pages.productsPage

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.domain.annotations.MainDispatcher
import com.effectivemobile.test.databinding.ProductsPageLayoutBinding
import com.effectivemobiletest.adapters.ProductPageAdapter
import com.effectivemobiletest.events.LoadingActions
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductsPageFragment: BaseFragment<ProductsPageLayoutBinding, ProductsViewModel>() {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val viewModel: ProductsViewModel by viewModels()
    override val binding: ProductsPageLayoutBinding by viewBinding(CreateMethod.INFLATE)

    override fun setUpViewsBinding() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.updatePage()
        }
        binding.productsPageRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.productsPageRecycler.itemAnimator = DefaultItemAnimator()
        binding.navigationBar.setupWithNavController(findNavController())
    }

    override fun ProductsViewModel.observeData() {
        mainPageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { data->
                lifecycleScope.launch(mainDispatcher){
                    binding.productsPageRecycler.adapter = ProductPageAdapter().apply {
                        items = data
                    }
                }
            }
        }
        loading.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { data->
                when(data){
                    is LoadingActions.ShowLoading -> binding.swipeLayout.isRefreshing = true
                    is LoadingActions.HideLoading -> binding.swipeLayout.isRefreshing = false
                }
            }
        }
        countOfProductsInCart.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { badgeValue->
                binding.navigationBar.setBadge(badgeValue)
            }
        }
        updatePage()
    }
}