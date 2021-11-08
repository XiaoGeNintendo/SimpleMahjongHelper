package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object OneToNine : Yaku {
    override fun getName(): String {
        return "一气通贯"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2,1)
    }

    override fun check(deck: Array<Int>): Boolean {
        val kz=Array(totalTile){false}
        for(i in 0..3){
            val gp=groupToTile[deck[i]]
            if(gp.second==RUN){
                kz[gp.first]=true
            }
        }

        return kz[Tile.M1.ordinal] && kz[Tile.M4.ordinal] && kz[Tile.M7.ordinal] ||
                kz[Tile.P1.ordinal] && kz[Tile.P4.ordinal] && kz[Tile.P7.ordinal] ||
                kz[Tile.S1.ordinal] && kz[Tile.S4.ordinal] && kz[Tile.S7.ordinal]
    }
}