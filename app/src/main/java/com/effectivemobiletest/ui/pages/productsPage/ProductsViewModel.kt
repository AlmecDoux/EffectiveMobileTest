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
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
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

    private val _openFilterPanel = MutableLiveData<Event<BottomSheet>>()
    val openFilterPanel = _openFilterPanel.asLiveData()

    private val _filterData = MutableLiveData<Event<List<DisplayableItem>>>()
    val filterData = _filterData.asLiveData()

    fun updatePage(){
        outLogs("UpdatePage")
        _loading.post(Event(LoadingActions.ShowLoading))
        updateDataPage()
    }

    private fun updateDataPage(){
        viewModelScope.launch {
            getHotSalesItems()
            getCountOfProductsInCart()
            getFilterData()
            joinAll()
            _loading.post(Event(LoadingActions.HideLoading))
        }

    }
    private fun getFilterData() {
        _filterData.post(Event(filtersDataBuilder))
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
                        defaultLocation = getString(R.string.city),
                        clickOnFilter = {
                            _openFilterPanel.post(Event(BottomSheet))
                        },
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
                        clickOn = { itemId->
                            _navigateEvent.navigate(ProductsPageFragmentDirections.actionProductsPageFragmentToDetailsProductFragment())
                        }
                    )
                )
                _mainPageData.post(Event(mainPage))
            }
            it.error?.let {
                _loading.post(Event(LoadingActions.HideLoading))
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
    private val filtersDataBuilder = listOf(
        StickyAdapterItem("Brand"),
        SpinnerAdapterItem(items = listOf(
            "Samsung",
            "Apple",
            "Huawei",
            "Xiaomi",
            "Motorola"
        )),
        StickyAdapterItem("Price"),
        SpinnerAdapterItem(items = listOf(
            "$300 - $500",
            "$501 - $1000",
            "$1001 - $3000",
            "$3001 - $5000",
            "$5001 - $10000",
        )),
        StickyAdapterItem("Size"),
        SpinnerAdapterItem(items = listOf(
            "4.5 to 5.5 inches",
            "6.0 to 6.5 inches",
            "6.5 to 6.5 inches",
            "8.0 to 9.0 inches",
            "9.5 to 10.0 inches",
        )),
    )
}