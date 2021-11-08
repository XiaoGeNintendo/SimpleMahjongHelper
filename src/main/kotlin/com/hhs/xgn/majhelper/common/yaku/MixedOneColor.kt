package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object MixedOneColor : Yaku {
    override fun getName(): String {
        return "混一色"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(3, 2)
    }

    override fun check(deck: Array<Int>): Boolean {
        if(FullOneColor.check(deck) || FullWord.check(deck) || FullGreen.check(deck)){
            return false
        }

        for (i in 1..3) {
            val gp = groupToTile[deck[i]]
            val last = groupToTile[deck[i - 1]]
            if (gp.first < Tile.East.ordinal) {
                if (gp.first / 9 != last.first / 9) {
                    return false
                }
            }
        }

        return deck[4] >= Tile.East.ordinal || deck[4] / 9 == groupToTile[deck[0]].first / 9
    }
}