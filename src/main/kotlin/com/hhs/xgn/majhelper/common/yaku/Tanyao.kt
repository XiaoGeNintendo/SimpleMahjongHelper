package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.containsOneNineTile
import com.hhs.xgn.majhelper.common.isRun
import com.hhs.xgn.majhelper.common.oneNineTiles
import com.hhs.xgn.majhelper.common.toDisplayName

object Tanyao : Yaku {
    override fun getName(): String {
        return "断幺九"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(1, 1)
    }

    override fun check(deck: Array<Int>): Boolean {
        return !containsOneNineTile(deck[0]) && !containsOneNineTile(deck[1])
                && !containsOneNineTile(deck[2]) && !containsOneNineTile(deck[3])
                && toDisplayName(deck[4]) !in oneNineTiles
    }
}