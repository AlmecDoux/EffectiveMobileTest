package com.effectivemobiletest.utils

import java.text.NumberFormat

class AmountDecorator {
    companion object{

        private fun getNormalAmountToString(amount: Int, fraction:Int):String{
            val doubleAmount = amount.toDouble()
            val format = NumberFormat.getNumberInstance()
            format.minimumFractionDigits = fraction
            format.maximumFractionDigits = 2
            return format.format(doubleAmount)
        }

        fun getNormalAmountToStringWithCurrency(amount: Int, fraction:Int = 0):String{
            val currency = "$"
            return if(currency.isNotEmpty()){
                currency+getNormalAmountToString(amount = amount, fraction)
            } else{
                getNormalAmountToString(amount = amount, fraction)
            }
        }
    }
}