package com.hhs.xgn.majhelper.gen

import com.hhs.xgn.majhelper.common.*
import java.io.PrintWriter
import java.lang.Integer.max

//generating table
fun genTable(): String {

    initIndex()
    initYaku()

    val str = ""

    var cnt = 0

    val pw = PrintWriter("table_${roundWind}_${myWind}.txt")

    for (i in 0 until totalGroup) {
        println("Progress: $i/$totalGroup")
        for (j in i until totalGroup) {
            for (k in j until totalGroup) {
                for (l in k until totalGroup) {
                    for (m in 0 until totalTile) {
                        val deck = arrayOf(i, j, k, l, m)
                        val score = checkScore(deck)

                        if (verifyDeck(deck)) {
                            cnt++
                            pw.print(compress(deck) + compress(score.first) + compress(score.second))
//                            println(deckToString(deck) + " " + score)
                        }
                    }
                }
            }
        }
    }

    pw.close()
    println("Done! $cnt.")


    return ""
}

fun main() {
    roundWind = EAST
    myWind = SOUTH

    genTable()
}