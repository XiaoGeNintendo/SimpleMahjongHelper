package com.hhs.xgn.majhelper.common.yaku

interface Yaku {
    fun getName():String

    /**
     * @return pair<menzen score, normal score>
     */
    fun getScore():Pair<Int,Int>

    fun check(deck: Array<Int>):Boolean
}