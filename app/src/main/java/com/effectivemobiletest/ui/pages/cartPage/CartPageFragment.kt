package com.effectivemobiletest.ui.pages.cartPage

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.CartPageLayoutBinding
import com.effectivemobiletest.adapters.minorAdapters.BasketAdapter
import com.effectivemobiletest.appComponent
import com.effectivemobiletest.ui.BaseFragment

class CartPageFragment:BaseFragment<CartPageLayoutBinding, CartPageViewModel>() {

    override val binding: CartPageLayoutBinding by viewBinding(CreateMethod.INFLATE)

    override fun Context.injectFragment() {
        appComponent.inject(this@CartPageFragment)
    }

    override fun setUpViewsBinding() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
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

    override fun createViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[CartPageViewModel::class.java]
    }
}