package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

class AllSet : Yaku {
    override fun getName(): String {
        return "对对和"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2,2)
    }

    override fun check(deck: Array<Int>): Boolean {
        for(i in 0..3){
            if(groupToTile[deck[i]].second!=SET){
                return false
            }
        }
        return true
    }
}