package com.effectivemobiletest.di.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.effectivemobiletest.ui.pages.cartPage.CartPageViewModel
import com.effectivemobiletest.ui.pages.detailsProductPage.DetailProductViewModel
import com.effectivemobiletest.ui.pages.productsPage.ProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CartPageViewModel::class)
    internal abstract fun bindCartPageViewModel(viewModel: CartPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailProductViewModel::class)
    internal abstract fun bindDetailProductViewModel(viewModel: DetailProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    internal abstract fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel
}