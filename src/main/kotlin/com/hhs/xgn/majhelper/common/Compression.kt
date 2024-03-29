package com.hhs.xgn.majhelper.common

val COMPRESS_KEY = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890-=+[]{}|;:,./<>?"

fun compress(value: Int): String {
    return COMPRESS_KEY[value] + ""
}

fun decompress(value: Char): Int {
    return COMPRESS_KEY.indexOf(value)
}

fun compress(arr: Array<Int>): String {
    val str = StringBuilder()
    for (i in arr) {
        str.append(compress(i))
    }
    return str.toString()
}