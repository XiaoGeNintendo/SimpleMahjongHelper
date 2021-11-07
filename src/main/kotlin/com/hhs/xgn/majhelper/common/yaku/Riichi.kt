package com.hhs.xgn.majhelper.common.yaku

class Riichi: Yaku {
    override fun getName(): String {
        return "立直"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1,0)
    }

    override fun check(deck: Array<Int>): Boolean {
        return true
    }
}