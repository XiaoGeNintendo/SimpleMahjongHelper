package com.hhs.xgn.majhelper.common

val COMPRESS_KEY="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890-=+[]{}|;:,./<>?"

fun compress(value: Int):String{
    return COMPRESS_KEY[value]+""
}

fun decompress(value: Char):Int{
    return COMPRESS_KEY.indexOf(value)
}

fun compress(arr: Array<Int>):String{
    var str=""
    for(i in arr){
        str+=compress(i)
    }
    return str
}