package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.isMyWindTile
import com.hhs.xgn.majhelper.common.isRoundWindTile
import com.hhs.xgn.majhelper.common.isRun

object Pinfu: Yaku {
    override fun getName(): String {
        return "平和"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1,0)
    }

    override fun check(deck: Array<Int>): Boolean {
        return isRun(deck[0]) && isRun(deck[1]) && isRun(deck[2]) && isRun(deck[3])
                && !isRoundWindTile(deck[4]) && !isMyWindTile(deck[4])
    }
}