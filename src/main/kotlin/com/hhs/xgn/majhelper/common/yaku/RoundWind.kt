package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object RoundWind : Yaku {
    override fun getName(): String {
        return "场风牌"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1, 1)
    }

    override fun check(deck: Array<Int>): Boolean {
        for (i in 0..3) {
            if (isRoundWindTile(groupToTile[deck[i]].first)) {
                return true
            }
        }
        return false
    }
}