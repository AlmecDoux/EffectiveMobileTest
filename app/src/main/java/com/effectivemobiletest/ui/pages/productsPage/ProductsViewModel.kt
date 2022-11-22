package com.effectivemobiletest.ui.pages.productsPage

import androidx.lifecycle.*
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobile.test.R
import com.effectivemobiletest.App
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.epoxy.models.mapperClasses.*
import com.effectivemobiletest.events.Event
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
):BaseViewModel(), LifecycleEventObserver {
    private val _mainPageData = MutableLiveData<Event<ArrayList<EpoxyData>>>()
    val mainPageData = _mainPageData.asLiveData()


    private suspend fun getHotSalesItems(){
        getMainPageDataUseCase.getMainPageData().collect{
            it.data?.let { mainPageData->
                val productPageContent = arrayListOf<EpoxyData>()
                productPageContent.add(EpoxyLocationItem(
                    listLocations = listOf("Zihuatanejo, Gro"),
                    clickOnFilter = { outLogs("clickFilter") },
                    chooseLocation = {}
                ))
                productPageContent.add(EpoxyHeaderTitleItem(
                    headerTitle = getString(R.string.select_category),
                    linkText = getString(R.string.view_all),
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                productPageContent.add(categoryDataBuilder.mapToEpoxy {
                    outLogs("click category: $it")
                })
                productPageContent.add(EpoxySearchItem(
                    search = { outLogs(it) },
                    qrBtnClick = {
                        outLogs("qrClick")
                    }
                ))
                productPageContent.add(EpoxyHeaderTitleItem(
                    headerTitle = getString(R.string.hot_sales),
                    linkText = getString(R.string.see_more),
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                productPageContent.add(mainPageData.hotSalesItems.mapToEpoxy())
                productPageContent.add(EpoxyHeaderTitleItem(
                    headerTitle = getString(R.string.best_seller),
                    linkText = getString(R.string.see_more),
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                productPageContent.add(mainPageData.bestSellerItems.mapToEpoxy(
                    clickOn = {
                        _navigateEvent.navigate(ProductsPageFragmentDirections.actionProductsPageFragmentToDetailsProductFragment())
                    },
                    clickFavorite = { id, isFav->
                        outLogs("clickOnFav:$id $isFav")
                    }
                ))
                _mainPageData.post(Event(productPageContent))
            }
            it.error?.let {
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

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if(event == Lifecycle.Event.ON_CREATE){
            viewModelScope.launch {
                getHotSalesItems()
            }
        }
    }
}