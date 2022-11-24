package com.effectivemobiletest.ui.pages.productsPage

import android.app.Application
import androidx.lifecycle.*
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobile.test.R
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.adapters.DisplayableItem
import com.effectivemobiletest.adapters.adaptersData.*
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.events.LoadingActions
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.navigate
import com.effectivemobiletest.extensions.post
import com.effectivemobiletest.ui.BaseViewModel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel
@Inject constructor(
    private val application: Application,
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
                        defaultLocation = application.getString(R.string.city),
                        clickOnFilter = {
                            _openFilterPanel.post(Event(BottomSheet))
                        },
                        chooseLocation = {}
                    ),
                    HeaderTitleAdapterItem(
                        linkText = application.getString(R.string.view_all),
                        headerTitle = application.getString(R.string.select_category),
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
                        linkText = application.getString(R.string.see_more),
                        headerTitle = application.getString(R.string.hot_sales),
                        clickLink = {}
                    ),
                    HotSalesAdapterItem(
                        hotSalesItems = mainPageData.hotSalesItems
                    ),
                    HeaderTitleAdapterItem(
                        linkText = application.getString(R.string.see_more),
                        headerTitle = application.getString(R.string.best_seller),
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
        CategoryItem(id = 1, img = R.drawable.phone_icon, title = application.getString(R.string.phone)),
        CategoryItem(id = 2, img = R.drawable.computer_icon, title = application.getString(R.string.computer)),
        CategoryItem(id = 3, img = R.drawable.health_icon, title = application.getString(R.string.health)),
        CategoryItem(id = 4, img = R.drawable.books_icon, title = application.getString(R.string.books)),
        CategoryItem(id = 5, img = R.drawable.phone_icon, title = application.getString(R.string.phone)),
        CategoryItem(id = 6, img = R.drawable.computer_icon, title = application.getString(R.string.computer)),
        CategoryItem(id = 7, img = R.drawable.health_icon, title = application.getString(R.string.health)),
        CategoryItem(id = 8, img = R.drawable.books_icon, title = application.getString(R.string.books))

    )
    private val filtersDataBuilder = listOf(
        StickyAdapterItem(application.getString(R.string.brand)),
        SpinnerAdapterItem(items = listOf(
            "Samsung",
            "Apple",
            "Huawei",
            "Xiaomi",
            "Motorola"
        )),
        StickyAdapterItem(application.getString(R.string.price)),
        SpinnerAdapterItem(items = listOf(
            "$300 - $500",
            "$501 - $1000",
            "$1001 - $3000",
            "$3001 - $5000",
            "$5001 - $10000",
        )),
        StickyAdapterItem(application.getString(R.string.size)),
        SpinnerAdapterItem(items = listOf(
            "4.5 to 5.5 inches",
            "6.0 to 6.5 inches",
            "6.5 to 6.5 inches",
            "8.0 to 9.0 inches",
            "9.5 to 10.0 inches",
        )),
    )
}