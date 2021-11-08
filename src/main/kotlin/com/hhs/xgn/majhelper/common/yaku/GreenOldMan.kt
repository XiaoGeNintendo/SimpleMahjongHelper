package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object GreenOldMan : Yaku {
    override fun getName(): String {
        return "清老头！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13,13)
    }

    override fun check(deck: Array<Int>): Boolean {
        for (i in 0..3) {
            val gp = groupToTile[deck[i]]
            if (gp.second != SET || toDisplayName(gp.first) !in oneNineTiles || gp.first>=Tile.East.ordinal) {
                return false
            }
        }

        if (toDisplayName(deck[4]) !in oneNineTiles || deck[4]>=Tile.East.ordinal) {
            return false
        }
        return true
    }
}