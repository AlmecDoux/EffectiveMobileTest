package com.effectivemobiletest.ui.pages.tabs.productsPage

import androidx.lifecycle.*
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobile.test.R
import com.effectivemobiletest.App
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.epoxy.models.mapperClasses.*
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject constructor(
    private val getMainPageDataUseCase: GetMainPageDataUseCase
):ViewModel(), LifecycleEventObserver {
    private val _mainPageData = MutableLiveData<Event<ArrayList<EpoxyData>>>()
    val mainPageData = _mainPageData.asLiveData()


    private suspend fun getHotSalesItems(){
        getMainPageDataUseCase.getMainPageData().collect{
            it.data?.let { mainPageData->
                val bestSalesAdapterModel = arrayListOf<EpoxyData>()
                bestSalesAdapterModel.add(EpoxyLocationItem(
                    listLocations = listOf("Zihuatanejo, Gro"),
                    clickOnFilter = { outLogs("clickFilter") },
                    chooseLocation = {}
                ))
                bestSalesAdapterModel.add(EpoxyHeaderTitleItem(
                    headerTitle = "Select Category",
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                bestSalesAdapterModel.add(categoryDataBuilder.mapToEpoxy {
                    outLogs("click category: $it")
                })
                bestSalesAdapterModel.add(EpoxySearchItem(
                    search = { outLogs(it) },
                    qrBtnClick = {
                        outLogs("qrClick")
                    }
                ))
                bestSalesAdapterModel.add(EpoxyHeaderTitleItem(
                    headerTitle = "Hot sales",
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                bestSalesAdapterModel.add(mainPageData.hotSalesItems.mapToEpoxy())
                bestSalesAdapterModel.add(EpoxyHeaderTitleItem(
                    headerTitle = "Best Sellers",
                    clickLink = {
                        outLogs("CLICK LINK")
                    }
                ))
                bestSalesAdapterModel.add(mainPageData.bestSellerItems.mapToEpoxy(
                    clickOn = {
                        outLogs("clickOnItem:$it")
                    },
                    clickFavorite = { id, isFav->
                        outLogs("clickOnFav:$id $isFav")
                    }
                ))
                _mainPageData.post(Event(bestSalesAdapterModel))
            }
            it.error?.let {
                App.outLogs("error $it")
            }
        }
    }

    private suspend fun getCategoriesItems(){
        //_categoryData.post(Event(categoryDataBuilder))
    }

    private val categoryDataBuilder = arrayListOf(
        CategoryItem(id = 1, img = R.drawable.phone_icon, title = "Phone"),
        CategoryItem(id = 2, img = R.drawable.search_icon, title = "Phone2"),
        CategoryItem(id = 3, img = R.drawable.phone_icon, title = "Phone3"),
        CategoryItem(id = 4, img = R.drawable.phone_icon, title = "Phone4"),
        CategoryItem(id = 5, img = R.drawable.phone_icon, title = "Phone5"),
        CategoryItem(id = 6, img = R.drawable.phone_icon, title = "Phone6"),
        CategoryItem(id = 7, img = R.drawable.phone_icon, title = "Phone7"),
        CategoryItem(id = 8, img = R.drawable.phone_icon, title = "Phone8"),
        CategoryItem(id = 9, img = R.drawable.phone_icon, title = "Phone9"),

    )

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if(event == Lifecycle.Event.ON_CREATE){
            viewModelScope.launch {
                getCategoriesItems()
                getHotSalesItems()
            }
        }
    }
}