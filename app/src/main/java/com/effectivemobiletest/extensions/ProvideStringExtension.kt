package com.effectivemobiletest.extensions

import com.effectivemobiletest.ui.BaseViewModel

fun BaseViewModel.getString(resId:Int):String{
    return this.appContext.getString(resId)
}