package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object BigFourHappiness : Yaku {
    override fun getName(): String {
        return "大四喜！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13,13)
    }

    override fun check(deck: Array<Int>): Boolean {
        val sy=Array(10){false}
        for(i in 0..3){
            val gp=groupToTile[deck[i]]
            if(gp.first>=Tile.East.ordinal && gp.first<=Tile.North.ordinal){
                sy[gp.first-Tile.East.ordinal]=true
            }
        }
        return sy[0] && sy[1] && sy[2] && sy[3]
    }
}