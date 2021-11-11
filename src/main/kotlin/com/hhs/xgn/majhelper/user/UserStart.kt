package com.hhs.xgn.majhelper.user

import com.hhs.xgn.majhelper.common.*
import java.io.File
import java.util.*
import javax.swing.JFrame
import javax.swing.JOptionPane
import kotlin.concurrent.thread
import kotlin.system.exitProcess

val arr = ArrayList<Pair<Array<Int>, Pair<Int, Int>>>()

const val MaxSuggestion = 20

fun gTSoIL(): TreeSet<Pair<Int, Long>> = TreeSet { pair: Pair<Int, Long>, pair1: Pair<Int, Long> ->
    pair.second.compareTo(pair1.second)
}

fun <T> SortedSet<T>.merge(obj: T) {
    this.add(obj)
    if (this.size > MaxSuggestion) {
        this.remove(this.last())
    }
}

fun analyze(
    completeDeck: Array<Int>,
    threadCount: Int,
    best1: SortedSet<Pair<Int, Long>>,
    best2: SortedSet<Pair<Int, Long>>
) {
    val threads = ArrayList<Thread>()
    for (j in 0 until threadCount) {
        threads.add(thread {
            val bestLocal1 = gTSoIL()
            val bestLocal2 = gTSoIL()
            for ((id, i) in arr.withIndex()) {
                if (id % threadCount == j) {
//                    val step = countSteps(completeDeck, i.first)
                    val diff = countDifficulty(completeDeck, i.first)
                    bestLocal1.merge(Pair(id, diff))
                    if (i.second.second != 0) {
                        bestLocal2.merge(Pair(id, diff))
                    }
                }
            }

            for (i in bestLocal1) {
                best1.merge(i)
            }
            for (i in bestLocal2) {
                best2.merge(i)
            }
        })
    }
    threads.forEach {
        it.join()
    }

    println("==============================")
    println("【门前清】")
    var cnt = 1
    for (i in best1) {
        println(
            "#${cnt}：牌型：[${deckToString(arr[i.first].first)}] " +
                    "${arr[i.first].second.first}番${countSteps(completeDeck, arr[i.first].first) - 1}向听 " +
                    "包括${getDetail(arr[i.first].first)}。" +
                    "难度：【${i.second}】，" +
                    "进出张：${getInOut(completeDeck, arr[i.first].first)}"
        )
        cnt++
    }

    println("==============================")
    println("【有副露】")
    cnt = 1
    for (i in best2) {
        println(
            "#${cnt}：牌型：[${deckToString(arr[i.first].first)}] " +
                    "${arr[i.first].second.second}番${countSteps(completeDeck, arr[i.first].first) - 1}向听 " +
                    "包括${getDetail(arr[i.first].first, true)}。" +
                    "难度：【${i.second}】，" +
                    "进出张：${getInOut(completeDeck, arr[i.first].first)}"
        )
        cnt++
    }
}

val scanner = Scanner(System.`in`)

fun userStart(threadCount: Int) {


//    println("Do you want to keep menzen:")
//    val menzen=scanner.nextBoolean()

    val best1 = Collections.synchronizedSortedSet(gTSoIL())

    val best2 = Collections.synchronizedSortedSet(gTSoIL())


//    repeat(128) { best1.add(Pair(-1,-1));best2.add(Pair(-1,-1)) }

    println("请用后缀表示法输入当前手牌:")
    val myDeck = scanner.next()
    val completeDeck = suffixToCompleteDeck(myDeck)

    println("读入牌型:" + completeDeckToString(completeDeck))
    println("分析中...可能需要几秒钟")

    analyze(completeDeck, threadCount, best1, best2)
//    while (true) {
//        println("==============================")
//        println("输入?查看全部命令>")
//        val cmd=scanner.next()
//
//        if(cmd=="?"){
//            println("""帮助菜单
//                R - 重新启动程序
//                E - 结束程序
//                +[牌] - 打入手牌，例如"+1m2m3m"
//                -[牌] - 打出手牌，例如"-1m2m3m"
//                ? - 显示此帮助
//                *[牌] - 过滤器白名单加入。只显示
//            """.trimMargin())
//        }else if(cmd=="R"){
//            return
//        }else if(cmd=="E"){
//            exitProcess(0)
//        }else{
//            println("我不懂。请使用?命令。")
//        }
//
//    }
}

fun main() {
    initIndex()
    initYaku()

    var threadCount = 0
    try {
        println("输入场风：东=0,西=1,南=2,北=3")
        roundWind = scanner.nextInt()
        println("输入自风：东=0,西=1,南=2,北=3")
        myWind = scanner.nextInt()
        println("线程个数。推荐6-12。")
        threadCount = scanner.nextInt()
    } catch (e: Exception) {

        val jf = JFrame()
        jf.isAlwaysOnTop = true
        JOptionPane.showMessageDialog(null, "输入过程发生了异常：\n请在命令行中打开程序并不要阻塞输入流。\n请检查输入数据的合法性。")
        exitProcess(2)
    }

    println("读表中，可能需要十几秒钟……")
    //read file
    var str = ""
    try {
        val reader = File("table_${roundWind}_$myWind.txt").bufferedReader()
        str = reader.readLine()
        reader.close()
    } catch (e: Exception) {
        println("读取表文件时发生错误。")
        println("1. 请检查输入数据是否正常")
        println("2. 请检查table_${roundWind}_$myWind.txt是否存在。若不存在，请调用生成函数gen。")
        exitProcess(1)
    }

    for (i in str.indices step 7) {
        arr.add(
            Pair(
                arrayOf(
                    decompress(str[i]),
                    decompress(str[i + 1]),
                    decompress(str[i + 2]),
                    decompress(str[i + 3]),
                    decompress(str[i + 4])
                ),
                Pair(decompress(str[i + 5]), decompress(str[i + 6]))
            )
        )
    }

    str = ""
    println("Read ${arr.size} different endings!")

    while (true) {
        userStart(threadCount)
    }
}