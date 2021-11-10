package com.hhs.xgn.majhelper.gen

import com.hhs.xgn.majhelper.common.*
import com.hhs.xgn.majhelper.user.scanner
import java.io.PrintWriter
import java.util.*
import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.system.exitProcess

//generating table
fun genTable() {

    initIndex()
    initYaku()

    val str = ""

    var cnt = 0

    val pw = PrintWriter("table_${roundWind}_${myWind}.txt")
    val threads = ArrayList<Thread>()
    for (i in 0 until totalGroup) {
        threads.add(thread {
            val tmp = StringBuilder()
            var tmpcnt = 0
            val deck = Array(5) { 0 }
            deck[0] = i
            for (j in i until totalGroup) {
                deck[1] = j
                for (k in j until totalGroup) {
                    deck[2] = k
                    for (l in k until totalGroup) {
                        deck[3] = l
                        for (m in 0 until totalTile) {
                            deck[4] = m
                            if (verifyDeck(deck)) {
                                val score = checkScore(deck)
                                tmpcnt++
                                tmp.append(compress(deck))
                                tmp.append(compress(score.first))
                                tmp.append(compress(score.second))
//                                pw.print(compress(deck) + compress(score.first) + compress(score.second))
//                            println(deckToString(deck) + " " + score)
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(100000)
            } catch (err: InterruptedException) {

            }
            pw.print(tmp)
            cnt += tmpcnt
        })
    }
    for (i in 1..totalGroup) {
        println("Progress: $i/$totalGroup")
        threads[totalGroup - i].interrupt()
        threads[totalGroup - i].join()
    }

    pw.close()
    println("Done! $cnt.")
}

fun main() {

    val scanner= Scanner(System.`in`)
    try {
        println("输入场风：东=0,西=1,南=2,北=3")
        roundWind = scanner.nextInt()
        println("输入自风：东=0,西=1,南=2,北=3")
        myWind = scanner.nextInt()
        println("开始生成，大概需要1分钟。")
    }catch(e: Exception){
        val jf= JFrame()
        jf.isAlwaysOnTop=true
        JOptionPane.showMessageDialog(jf,"输入过程发生了异常：\n请在命令行中打开程序并不要阻塞输入流。\n请检查输入数据的合法性。")
        exitProcess(2)
    }

    genTable()
}