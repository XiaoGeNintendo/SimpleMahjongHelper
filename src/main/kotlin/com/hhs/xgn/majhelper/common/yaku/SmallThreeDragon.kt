package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

class SmallThreeDragon : Yaku {
    override fun getName(): String {
        return "小三元"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2,2)
    }

    override fun check(deck: Array<Int>): Boolean {
        val sy=Array(10){false}
        for(i in 0..3){
            if(groupToTile[deck[i]].first>=Tile.White.ordinal){
                sy[groupToTile[deck[i]].first-Tile.White.ordinal]=true
            }
        }
        if(deck[4]>=Tile.White.ordinal) {
            sy[deck[4]-Tile.White.ordinal]=true
        }
        return sy[0] && sy[1] && sy[2]
    }
}