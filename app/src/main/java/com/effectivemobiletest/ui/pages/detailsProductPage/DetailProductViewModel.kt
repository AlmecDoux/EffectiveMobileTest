package com.effectivemobiletest.ui.pages.detailsProductPage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.effectivemobile.domain.useCases.GetProductDetailsUseCase
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.post
import com.effectivemobiletest.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailProductViewModel
@Inject constructor(
    private val productDetailsUseCase: GetProductDetailsUseCase
):BaseViewModel() {

    private val _productDetailsData = MutableLiveData<Event<DetailsPageData>>()
    val productDetailsData = _productDetailsData.asLiveData()

    fun getProductData() {
        viewModelScope.launch {
            getProductDetails()
        }
    }

    private suspend fun getProductDetails() {
        productDetailsUseCase.getProductDetails().collect{
            it.data?.let { productDetails->
                _productDetailsData.post(Event(productDetails.mapToDetailsData()))
            }
            it.error?.let {

            }
        }
    }

}