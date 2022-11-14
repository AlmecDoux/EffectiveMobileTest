package com.effectivemobiletest

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application(){
    companion object{
        fun outLogs(msg:String){
            Log.i("APP_EFFECTIVE", msg)
        }
    }
}