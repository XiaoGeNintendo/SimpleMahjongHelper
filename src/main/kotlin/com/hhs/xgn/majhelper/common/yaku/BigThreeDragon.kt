package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object BigThreeDragon : Yaku {
    override fun getName(): String {
        return "大三元！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13, 13)
    }

    override fun check(deck: Array<Int>): Boolean {
        var ord=0
        for(i in 0..3){
            val gp=groupToTile[deck[i]]
            if(gp.first>=Tile.White.ordinal) {
                ord = ord or 1 shl gp.first-Tile.White.ordinal
            }
        }

        return ord==0b111
    }
}