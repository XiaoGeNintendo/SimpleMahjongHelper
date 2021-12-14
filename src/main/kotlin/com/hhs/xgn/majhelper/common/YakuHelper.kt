package com.hhs.xgn.majhelper.common

import com.hhs.xgn.majhelper.common.yaku.*

val yakus = ArrayList<Yaku>()
var isYakuInit = false

fun initYaku() {
    if (isYakuInit) {
        return
    }
    isYakuInit = true
    yakus += DoubleSequence
    yakus += Pinfu
//    yakus += Riichi
    yakus += Tanyao
    yakus += RoundWind
    yakus += SelfWind
    yakus += ThreeYuan
    yakus += OneToNine
    yakus += ThreeRunOfSameKind
    yakus += ThreeSetOfSameKind
    yakus += MixedOneNine
    yakus += AllSet
    yakus += MixedOldMan
    yakus += SmallThreeDragon
    yakus += DoubleDoubleSequence
    yakus += FullOneNine
    yakus += MixedOneColor
    yakus += FullOneColor
    yakus += FullGreen
    yakus += FullWord
    yakus += GreenOldMan
    yakus += NineLight
    yakus += SmallFourHappiness
    yakus += BigFourHappiness
    yakus += BigThreeDragon

}

fun getDetail(deck: Array<Int>, notClean: Boolean = false):ArrayList<String>{
    initYaku()

    val res=ArrayList<String>()

    for(i in yakus){
        if(i.check(deck)){
            if(notClean && i.getScore().second==0){
                continue
            }
            res.add(i.getName())
        }
    }

    return res
}

fun checkScore(deck: Array<Int>):Pair<Int,Int> {
    initYaku()
    var s0=0
    var s1=0
    for(i in yakus){
        if(i.check(deck)){
            s0+=i.getScore().first
            s1+=i.getScore().second
        }
    }

    return Pair(s0,s1)
}