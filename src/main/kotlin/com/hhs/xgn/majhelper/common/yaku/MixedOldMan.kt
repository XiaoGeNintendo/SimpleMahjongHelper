package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

class MixedOldMan : Yaku {
    override fun getName(): String {
        return "混老头"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2, 2)
    }

    override fun check(deck: Array<Int>): Boolean {
        for (i in 0..3) {
            val gp = groupToTile[deck[i]]
            if (gp.second != SET || toDisplayName(gp.first) !in oneNineTiles) {
                return false
            }
        }

        if (toDisplayName(deck[4]) !in oneNineTiles) {
            return false
        }
        return true
    }
}