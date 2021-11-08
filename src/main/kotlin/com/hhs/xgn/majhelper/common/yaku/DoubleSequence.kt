package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.isRun

object DoubleSequence : Yaku {
    override fun getName(): String {
        return "一杯口"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1, 0)
    }

    override fun check(deck: Array<Int>): Boolean {
        return (isRun(deck[0]) && deck[0] == deck[1] || isRun(deck[1]) && deck[1] == deck[2] ||
                isRun(deck[2]) && deck[2] == deck[3]) &&
                !DoubleDoubleSequence.check(deck)
    }
}