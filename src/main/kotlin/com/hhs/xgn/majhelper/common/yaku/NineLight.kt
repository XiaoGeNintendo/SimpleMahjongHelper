package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object NineLight : Yaku {
    override fun getName(): String {
        return "九莲宝灯！！"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(13, 0)
    }

    fun checkFor(base: String, b2: String, b3: String):Boolean{
        for(i in b3){
            val target=(b2+i).toCharArray().sorted().joinToString("")
            if(base==target){
                return true
            }
        }
        return false
    }

    override fun check(deck: Array<Int>): Boolean {
        if(!FullOneColor.check(deck)){
            return false
        }
        val str= deckToString(deck)
        val s=str.toCharArray().sorted().joinToString("")
        return checkFor(s,"一一一二三四五六七八九九九","一二三四五六七八九") ||
                checkFor(s,"①①①②③④⑤⑥⑦⑧⑨⑨⑨","①②③④⑤⑥⑦⑧⑨") ||
                checkFor(s,"１１１２３４５６７８９９９","１２３４５６７８９")
    }
}