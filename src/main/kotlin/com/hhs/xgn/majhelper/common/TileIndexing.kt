package com.hhs.xgn.majhelper.common

import java.lang.Integer.max
import java.lang.Math.pow
import java.util.*
import kotlin.math.pow

const val SET = 0
const val RUN = 1
val tileString = "一二三四五六七八九①②③④⑤⑥⑦⑧⑨１２３４５６７８９东南西北白发中？"
val totalTile = tileString.length - 1 //exclude '?'
val oneNineTiles = "一九①⑨１９东西南北白发中"

/**
 * 0 = set
 * 1 = run
 */
val groupToTile = Array(250) { Pair(0, 0) }
var totalGroup = 0

fun isRun(group: Int): Boolean {
    return groupToTile[group].second == RUN
}

fun isSet(group: Int): Boolean {
    return groupToTile[group].second == SET
}

fun containsOneNineTile(group: Int): Boolean {
    return toDisplayName(groupToTile[group].first) in oneNineTiles ||
            groupToTile[group].second == RUN && toDisplayName(groupToTile[group].first) in "七⑦7"
}

fun isRoundWindTile(tile: Int): Boolean {
    return when (roundWind) {
        EAST -> tile == Tile.East.ordinal
        SOUTH -> tile == Tile.South.ordinal
        WEST -> tile == Tile.West.ordinal
        NORTH -> tile == Tile.North.ordinal
        else -> false
    }
}

fun isMyWindTile(tile: Int): Boolean {
    return when (myWind) {
        EAST -> tile == Tile.East.ordinal
        SOUTH -> tile == Tile.South.ordinal
        WEST -> tile == Tile.West.ordinal
        NORTH -> tile == Tile.North.ordinal
        else -> false
    }
}

fun isThreeDragon(tile: Int): Boolean {
    return tile >= Tile.White.ordinal
}

fun toDisplayName(tile: Int): String {
    return tileString[tile] + ""
}

fun toTileID(tile: String): Int {
    return tileString.indexOf(tile)
}

fun groupToString(group: Int): String {
    val a = groupToTile[group]
    return if (a.second == RUN) {
        toDisplayName(a.first) + toDisplayName(a.first + 1) + toDisplayName(a.first + 2)
    } else {
        toDisplayName(a.first) + toDisplayName(a.first) + toDisplayName(a.first)
    }
}

/**
 * Returns a readable format for a *deck*
 */
fun deckToString(deck: Array<Int>): String {
    return groupToString(deck[0]) + groupToString(deck[1]) + groupToString(deck[2]) + groupToString(deck[3]) + toDisplayName(
        deck[4]
    ) + toDisplayName(deck[4])
}

/**
 * Returns a readable format for a *complete deck*
 */
fun completeDeckToString(completeDeck: Array<Int>): String {
    var str = ""
    for (i in completeDeck) {
        str += toDisplayName(i)
    }
    return str
}

/**
 * Expand a *deck* to a *complete deck*
 */
@Deprecated("Too slow")
fun expandDeck(deck: Array<Int>): Array<Int> {
    val res = Array(14) { 0 }
    val str = deckToString(deck)
    for ((i, j) in str.withIndex()) {
        res[i] = toTileID(j + "")
    }
    return res
}

/**
 * Verify a *deck* to make sure it does not count too much item
 */
fun verifyDeck(deck: Array<Int>): Boolean {
    val cnt = Array(totalTile) { 0 }
    for (i in 0..3) {
        val gp = groupToTile[deck[i]]
        if (gp.second == RUN) {
            cnt[gp.first]++
            cnt[gp.first + 1]++
            cnt[gp.first + 2]++
        } else {
            cnt[gp.first] += 3
        }
    }
    cnt[deck[4]] += 2
    return !cnt.any { it > 4 }
}

fun countSteps(completeDeck: Array<Int>, deck: Array<Int>): Int {
    val arr = Array(totalTile + 1) { 0 }
    for (i in completeDeck) {
        arr[i]++
    }
    for (i in 0..3) {
        val gp = groupToTile[deck[i]]
        if (gp.second == RUN) {
            arr[gp.first]--
            arr[gp.first + 1]--
            arr[gp.first + 2]--
        } else {
            arr[gp.first] -= 3
        }
    }
    arr[deck[4]] -= 2

//    if(arr.sumOf { max(-it,0) }==15){
//        println(completeDeckToString(completeDeck) + " " + deckToString(deck))
//    }

    return arr.sumOf { max(-it, 0) }
}

/**
 * 进出张计算
 */
fun getInOut(completeDeck: Array<Int>, deck: Array<Int>): String {
    val arr = Array(totalTile + 1) { 0 }
    for (i in completeDeck) {
        arr[i]++
    }
    for (i in 0..3) {
        val gp = groupToTile[deck[i]]
        if (gp.second == RUN) {
            arr[gp.first]--
            arr[gp.first + 1]--
            arr[gp.first + 2]--
        } else {
            arr[gp.first] -= 3
        }
    }
    arr[deck[4]] -= 2

//    if(arr.sumOf { max(-it,0) }==15){
//        println(completeDeckToString(completeDeck) + " " + deckToString(deck))
//    }
    var str = ""

    for ((id, i) in arr.withIndex()) {
        if (id == totalTile) {
            continue
        }
        if (i > 0) {
            str += "弃[${toDisplayName(id)}]*$i "
        }
    }
    for ((id, i) in arr.withIndex()) {
        if (id == totalTile) {
            continue
        }
        if (i < 0) {
            str += "待[${toDisplayName(id)}]*${-i} "
        }
    }
    return str
}


/**
 * 难度计算
 */
fun countDifficulty(completeDeck: Array<Int>, deck: Array<Int>): Pair<Long,Long> {
    val arr = Array(totalTile + 1) { 0 }
    val has = Array(totalTile+1){0}
    for (i in completeDeck) {
        arr[i]++
        has[i]++
    }

    for (i in 0..3) {
        val gp = groupToTile[deck[i]]
        if (gp.second == RUN) {
            arr[gp.first]--
            arr[gp.first + 1]--
            arr[gp.first + 2]--
        } else {
            arr[gp.first] -= 3
        }
    }
    arr[deck[4]] -= 2

    val listen=arr.sumOf{max(-it,0)}
    var diff1= (3.0.pow(listen)).toLong()
    var diff2= (3.0.pow(listen)).toLong()
    val diffFactor= arrayOf(2,5,13,23)
    val diffFactor2 = arrayOf(2,5,2,13)

    for(i in 0 until totalTile){
        while(arr[i]<0){
            diff1*=diffFactor[has[i]]
            diff2*=diffFactor2[has[i]]
            arr[i]++
            has[i]++
        }
    }

//    println(completeDeckToString(completeDeck)+" "+ deckToString(deck)+" = $diff")
//    Thread.sleep(1000)
    return Pair(diff1,diff2)
}

fun suffixToCompleteDeck(suffix: String): Array<Int> {
    val nw = suffix.lowercase()

    val arr = Array(14) { totalTile }
    var cnt = 0

    var mode = 0
    for (i in suffix.indices.reversed()) {
        when (suffix[i]) {
            'z' -> mode = 26
            'p' -> mode = 8
            's' -> mode = 17
            'm' -> mode = -1
            else -> {
                arr[cnt] = mode + (suffix[i] - '0')
                cnt++
            }
        }
    }

    return arr.reversedArray()
}

fun initIndex() {
    for (i in 0..33) {
        if (i < 27) {
            if (i % 9 + 1 <= 7) {
                groupToTile[totalGroup] = Pair(i, RUN)
                totalGroup++
            }
        }

        groupToTile[totalGroup] = Pair(i, SET)
        totalGroup++
    }
}