package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object ThreeSetOfSameKind : Yaku {
    override fun getName(): String {
        return "三色同刻"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2, 2)
    }

    override fun check(deck: Array<Int>): Boolean {
        val arr = Array(10) { 0 }
        for (i in 0..3) {
            val gp = groupToTile[deck[i]]
            if (gp.second == SET && gp.first < Tile.East.ordinal) {
                arr[gp.first % 9] = arr[gp.first % 9] or (1 shl gp.first / 9)
            }
        }

        return arr.any { it == 0b111 }
    }
}