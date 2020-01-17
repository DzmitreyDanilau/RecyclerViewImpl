package com.example.recyclerviewimpl.utils

import java.math.BigDecimal


fun Double.roundAndCastToInt(): Int {
    return BigDecimal(this).setScale(2, BigDecimal.ROUND_DOWN).toInt()
}

fun Double.roundAndCastToString(): String {
    return BigDecimal(this).setScale(2, BigDecimal.ROUND_DOWN).toInt().toString()
}