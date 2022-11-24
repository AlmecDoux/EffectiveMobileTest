package com.effectivemobiletest.di

import android.app.Application
import com.effectivemobiletest.di.viewModelFactory.ViewModelModule
import com.effectivemobiletest.ui.pages.cartPage.CartPageFragment
import com.effectivemobiletest.ui.pages.detailsProductPage.DetailsProductFragment
import com.effectivemobiletest.ui.pages.productsPage.ProductsPageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    DispatcherModule::class])

interface AppComponent {
    fun inject(fragment: ProductsPageFragment)
    fun inject(fragment: DetailsProductFragment)
    fun inject(fragment: CartPageFragment)

    @Component.Builder
    interface Builder {
        fun build():AppComponent
        @BindsInstance
        fun application(application: Application):Builder
    }
}