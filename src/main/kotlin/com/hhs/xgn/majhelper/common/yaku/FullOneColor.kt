package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object FullOneColor : Yaku {
    override fun getName(): String {
        return "清一色"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(6,5)
    }

    override fun check(deck: Array<Int>): Boolean {
        for (i in 1..3) {
            val gp = groupToTile[deck[i]]
            val last = groupToTile[deck[i - 1]]
            if (gp.first>=Tile.East.ordinal || gp.first / 9 != last.first / 9) {
                return false
            }
        }

        return deck[4] / 9 == groupToTile[deck[0]].first / 9
    }
}