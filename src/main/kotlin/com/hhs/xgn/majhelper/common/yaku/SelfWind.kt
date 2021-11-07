package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

class SelfWind : Yaku {
    override fun getName(): String {
        return "自风牌"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1, 1)
    }

    override fun check(deck: Array<Int>): Boolean {
        for (i in 0..3) {
            if (isMyWindTile(groupToTile[deck[i]].first)) {
                return true
            }
        }
        return false
    }
}