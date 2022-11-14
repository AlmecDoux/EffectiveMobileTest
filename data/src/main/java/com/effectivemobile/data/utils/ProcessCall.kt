package com.effectivemobile.data.utils

import retrofit2.Response
import java.io.IOException

suspend fun processCall(responseCall: suspend () -> Response<*>): Any {
    return try {
        val response = responseCall.invoke()
        if (response.isSuccessful) {
            if(response.body() == null){
                "Something went wrong"
            }
            else{
                response.body()!!
            }
        } else {
            if (response.errorBody() != null) {
                response.errorBody()!!.string()
            } else{
                "Something went wrong"
            }
        }
    } catch (e: IOException) {
        if (e.message != null) {
            e.message!!
        } else{
            "Something went wrong"
        }
    }
}