package com.effectivemobiletest.ui.pages.tabs.productsPage

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivemobile.domain.repository.MainPageDataRepository
import com.effectivemobile.domain.useCases.GetMainPageDataUseCase
import com.effectivemobiletest.App
import com.effectivemobiletest.adapters.adaptersModels.HotSalesAdapterModel
import com.effectivemobiletest.adapters.delegateAdapter.DelegateAdapterItem
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel
@Inject constructor(
    private val getMainPageDataUseCase: GetMainPageDataUseCase
):ViewModel() {
    private val _pageData = MutableLiveData<Event<List<DelegateAdapterItem>>>()
    val pageData = _pageData.asLiveData()

    init {
        viewModelScope.launch {
            getMainPageData()
        }
    }

    private suspend fun getMainPageData(){
        getMainPageDataUseCase.getMainPageData().collect{
            it.data?.let { mainPageData->
                App.outLogs("data $mainPageData")
                var hotSalesAdapterModel = listOf<DelegateAdapterItem>()
                mainPageData.hotSalesItems.forEach{
                    hotSalesAdapterModel = hotSalesAdapterModel.plus(HotSalesAdapterModel(hotSaleItem = it))
                }
                _pageData.post(Event(hotSalesAdapterModel))
            }
            it.error?.let {
                App.outLogs("error $it")
            }
        }
    }
}