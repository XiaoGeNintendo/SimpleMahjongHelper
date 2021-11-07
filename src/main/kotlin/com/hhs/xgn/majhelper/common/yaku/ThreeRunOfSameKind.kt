package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

class ThreeRunOfSameKind : Yaku {
    override fun getName(): String {
        return "三色同顺"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2,1)
    }

    override fun check(deck: Array<Int>): Boolean {
        val arr=Array(10){0}
        for(i in 0..3){
            val gp= groupToTile[deck[i]]
            if(gp.second==RUN){
                arr[gp.first%9] = arr[gp.first%9] or (1 shl gp.first/9)
            }
        }

        return arr.any { it==0b111 }
    }
}