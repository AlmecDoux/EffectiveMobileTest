package com.effectivemobiletest.ui.pages.cartPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.effectivemobile.domain.useCases.GetProductUserCartUseCase
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.events.Event
import com.effectivemobiletest.extensions.asLiveData
import com.effectivemobiletest.extensions.post
import com.effectivemobiletest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartPageViewModel
@Inject constructor(
    private val getProductUserCartUseCase: GetProductUserCartUseCase
):BaseViewModel() {

    private val _cartPageData = MutableLiveData<Event<CartPageData>>()
    val cartPageData = _cartPageData.asLiveData()

    private suspend fun getProductCart(){
        getProductUserCartUseCase.getUserCart().collect{
            it.data?.let {  userCartData->
                _cartPageData.post(Event(userCartData.mapToPageData()))
            }
            it.error?.let { error->
                outLogs(error)
            }
        }
    }
    fun loadPageData(){
        viewModelScope.launch {
            getProductCart()
        }
    }
}