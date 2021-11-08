package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object FullWord : Yaku {
    override fun getName(): String {
        return "字一色！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13, 13)
    }

    override fun check(deck: Array<Int>): Boolean {
        for(i in 0..3){
            val gp=groupToTile[deck[i]]
            if(gp.first<Tile.East.ordinal) {
                return false
            }
        }

        return deck[4]>=Tile.East.ordinal
    }
}