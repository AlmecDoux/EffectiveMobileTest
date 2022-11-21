package com.effectivemobiletest.ui.pages.detailsProductPage

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.effectivemobile.domain.useCases.GetProductDetailsUseCase
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.post
import com.effectivemobiletest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel
@Inject constructor(
    private val productDetailsUseCase: GetProductDetailsUseCase
):BaseViewModel(), LifecycleEventObserver {

    private val _photosData = MutableLiveData<Event<ArrayList<String>>>()
    val photosData = _photosData.asLiveData()

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if(event == Lifecycle.Event.ON_CREATE){
            viewModelScope.launch {
                getProductDetails()
            }
        }
    }

    private suspend fun getProductDetails() {
        productDetailsUseCase.getProductDetails().collect{
            it.data?.let { productDetails->
                _photosData.post(Event(productDetails.images))
                outLogs(productDetails.toString())
            }
            it.error?.let {

            }
        }
    }

}