package com.effectivemobiletest.ui.pages.tabs.productsPage

import androidx.lifecycle.*
import com.effectivemobile.domain.models.subtypes.CategoryItem
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobile.test.R
import com.effectivemobiletest.App
import com.effectivemobiletest.adapters.adapters.CategoriesAdapter
import com.effectivemobiletest.adapters.adaptersModels.CategoriesAdapterModel
import com.effectivemobiletest.adapters.adaptersModels.HotSalesAdapterModel
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem
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
    private val _hotSalesData = MutableLiveData<Event<List<DelegateAdapterItem>>>()
    val hotSalesData = _hotSalesData.asLiveData()

    private val _categoryData = MutableLiveData<Event<List<DelegateAdapterItem>>>()
    val categoryData = _categoryData.asLiveData()


    private suspend fun getHotSalesItems(){
        getMainPageDataUseCase.getMainPageData().collect{
            it.data?.let { mainPageData->
                App.outLogs("data $mainPageData")
                val hotSalesAdapterModel = arrayListOf<DelegateAdapterItem>()
                mainPageData.hotSalesItems.forEach{
                    hotSalesAdapterModel.add(HotSalesAdapterModel(hotSaleItem = it))
                }
                _hotSalesData.post(Event(hotSalesAdapterModel))
            }
            it.error?.let {
                App.outLogs("error $it")
            }
        }
    }

    private suspend fun getCategoriesItems(){
        _categoryData.post(Event(categoryDataBuilder))
    }

    private val categoryDataBuilder = arrayListOf<DelegateAdapterItem>(
        CategoriesAdapterModel(CategoryItem(id = 1, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 2, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 3, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 4, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 5, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 6, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 7, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 8, img = R.drawable.phone_icon, title = "Phone")),
        CategoriesAdapterModel(CategoryItem(id = 9, img = R.drawable.phone_icon, title = "Phone")),

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