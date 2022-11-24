package com.effectivemobiletest

import android.app.Application
import android.content.Context
import android.util.Log
import com.effectivemobiletest.di.AppComponent
import com.effectivemobiletest.di.DaggerAppComponent


class App:Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    }
    companion object{
        fun outLogs(msg:String){
            Log.i("APP_EFFECTIVE", msg)
        }
    }
}

val Context.appComponent:AppComponent
get() = when(this){
    is App->this.appComponent
    else->this.applicationContext.appComponent
}