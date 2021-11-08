package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object FullGreen : Yaku {
    override fun getName(): String {
        return "绿一色！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13, 13)
    }

    override fun check(deck: Array<Int>): Boolean {
        for(i in 0..3){
            val gp=groupToTile[deck[i]]
            if(gp.second==SET && toDisplayName(gp.first) in "２３４６８发" ||
                    gp.second==RUN && gp.first==Tile.S2.ordinal) {
                //very good
            }else{
                return false
            }
        }

        return toDisplayName(deck[4]) in "２３４６８发"
    }
}