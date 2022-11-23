package com.effectivemobiletest.ui.pages.productsPage

import androidx.lifecycle.*
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobile.test.R
import com.effectivemobiletest.App
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.adapters.DisplayableItem
import com.effectivemobiletest.adapters.adaptersData.*
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.LoadingActions
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.getString
import com.effectivemobiletest.extensions.navigate
import com.effectivemobiletest.extensions.post
import com.effectivemobiletest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject constructor(
    private val getMainPageDataUseCase: GetMainPageDataUseCase
):BaseViewModel() {

    private val _mainPageData = MutableLiveData<Event<List<DisplayableItem>>>()
    val mainPageData = _mainPageData.asLiveData()

    private val _countOfProductsInCart = MutableLiveData<Event<Int>>()
    val countOfProductsInCart = _countOfProductsInCart.asLiveData()

    fun updatePage(){
        outLogs("UpdatePage")
        _loading.post(Event(LoadingActions.ShowLoading))
        viewModelScope.launch {
            getHotSalesItems()
            getCountOfProductsInCart()
        }
    }

    private suspend fun getCountOfProductsInCart() {
        getMainPageDataUseCase.getCountProductInUserCart().collect{
            _countOfProductsInCart.post(Event(it))
        }
    }

    private suspend fun getHotSalesItems(){
        getMainPageDataUseCase.getMainPageData().collect{
            it.data?.let { mainPageData ->
                val mainPage = listOf(
                    LocationAdapterItem(
                        defaultLocation = "City",
                        clickOnFilter = {},
                        chooseLocation = {}
                    ),
                    HeaderTitleAdapterItem(
                        linkText = getString(R.string.view_all),
                        headerTitle = getString(R.string.select_category),
                        clickLink = {}
                    ),
                    CategoryAdapterItems(
                        categoryItems = categoryDataBuilder
                    ),
                    SearchAdapterItem(
                        search = {
                            outLogs(it)
                        },
                        qrBtnClick = {}
                    ),
                    HeaderTitleAdapterItem(
                        linkText = getString(R.string.see_more),
                        headerTitle = getString(R.string.hot_sales),
                        clickLink = {}
                    ),
                    HotSalesAdapterItem(
                        hotSalesItems = mainPageData.hotSalesItems
                    ),
                    HeaderTitleAdapterItem(
                        linkText = getString(R.string.see_more),
                        headerTitle = getString(R.string.best_seller),
                        clickLink = {}
                    ),
                    BestSalesAdapterItem(
                        items = mainPageData.bestSellerItems,
                        clickOn = {
                            _navigateEvent.navigate(ProductsPageFragmentDirections.actionProductsPageFragmentToDetailsProductFragment())
                        }
                    )
                )
                _mainPageData.post(Event(mainPage))
                _loading.post(Event(LoadingActions.HideLoading))
            }
            it.error?.let {
                _loading.post(Event(LoadingActions.HideLoading))
                App.outLogs("error $it")
            }
        }
    }



    private val categoryDataBuilder = arrayListOf(
        CategoryItem(id = 1, img = R.drawable.phone_icon, title = "Phone"),
        CategoryItem(id = 2, img = R.drawable.computer_icon, title = "Computer"),
        CategoryItem(id = 3, img = R.drawable.health_icon, title = "Health"),
        CategoryItem(id = 4, img = R.drawable.books_icon, title = "Books"),
        CategoryItem(id = 5, img = R.drawable.phone_icon, title = "Phone"),
        CategoryItem(id = 6, img = R.drawable.computer_icon, title = "Computer"),
        CategoryItem(id = 7, img = R.drawable.health_icon, title = "Health"),
        CategoryItem(id = 8, img = R.drawable.books_icon, title = "Books")

    )

}