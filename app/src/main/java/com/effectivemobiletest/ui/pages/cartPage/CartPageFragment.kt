package com.effectivemobiletest.ui.pages.cartPage

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.CartPageLayoutBinding
import com.effectivemobiletest.adapters.minorAdapters.BasketAdapter
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartPageFragment:BaseFragment<CartPageLayoutBinding, CartPageViewModel>() {

    override val binding: CartPageLayoutBinding by viewBinding(CreateMethod.INFLATE)
    override val viewModel: CartPageViewModel by viewModels()

    override fun setUpViewsBinding() {

    }
    override fun CartPageViewModel.observeData() {
        cartPageData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { cartPageData ->
                binding.amountField.text = cartPageData.totalPrice
                binding.deliveryField.text = cartPageData.delivery
                binding.productInCardRecycler.adapter = BasketAdapter(cartPageData.basket)
            }
        }
        loadPageData()
    }
}