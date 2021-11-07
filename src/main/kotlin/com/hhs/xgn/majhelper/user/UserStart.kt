package com.hhs.xgn.majhelper.user

import com.hhs.xgn.majhelper.common.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

val arr=ArrayList<Pair<Array<Int>,Pair<Int,Int>>>()

fun userStart() {
    initIndex()
    initYaku()

    //read file
    val reader= File("table_${roundWind}_$myWind.txt").bufferedReader()
    val str=reader.readLine()
    reader.close()

    for(i in str.indices step 7){
        arr.add(Pair(
            arrayOf(decompress(str[i]), decompress(str[i+1]), decompress(str[i+2]), decompress(str[i+3]), decompress(str[i+4])),
            Pair(decompress(str[i+5]), decompress(str[i+6])))
        )
    }

    println("Read ${arr.size} different endings!")

    val scanner= Scanner(System.`in`)
//    println("Do you want to keep menzen:")
//    val menzen=scanner.nextBoolean()


    while(true){

        val best=Array(15){-1}
        println("Please enter your deck using suffix notation:")
        val myDeck=scanner.next()
        val completeDeck=suffixToCompleteDeck(myDeck)

        println("Input:"+completeDeckToString(completeDeck))
        println("Analyzing...")

        for((id,i) in arr.withIndex()){
            val step = countSteps(completeDeck,i.first)
            if(best[step]==-1 || arr[best[step]].second.first<i.second.first){
                best[step]=id
            }
        }

        for(i in 0 until 15){
            if(best[i]==-1){
                println("待${i}张牌无法和牌")
            }else {
                println("待${i}张牌最好牌型：[${deckToString(arr[best[i]].first)}] 共${arr[best[i]].second.first}番，包括${getDetail(arr[best[i]].first)}")
            }
        }
    }
}

fun main() {
    roundWind = EAST
    myWind = SOUTH
    userStart()
}