package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object DoubleDoubleSequence : Yaku {
    override fun getName(): String {
        return "二杯口"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(3,0)
    }

    override fun check(deck: Array<Int>): Boolean {
        var cnt=0
        var i=1
        while(i<=3){
            if(isRun(deck[i]) && deck[i]==deck[i-1]){
                cnt++
                i++
            }

            i++
        }

        return cnt>=2
    }
}