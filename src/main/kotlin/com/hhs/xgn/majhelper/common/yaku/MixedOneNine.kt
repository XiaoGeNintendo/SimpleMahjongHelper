package com.hhs.xgn.majhelper.common.yaku

import com.hhs.xgn.majhelper.common.*

object MixedOneNine : Yaku {
    override fun getName(): String {
        return "混全带幺九"
    }

    override fun getScore(): Pair<Int, Int> {
        return Pair(2,1)
    }

    override fun check(deck: Array<Int>): Boolean {
        return containsOneNineTile(deck[0]) && containsOneNineTile(deck[1]) &&
                containsOneNineTile(deck[2]) && containsOneNineTile(deck[3]) &&
                toDisplayName(deck[4]) in oneNineTiles &&
                //不能全部都是字牌
                !(groupToTile[deck[0]].first>=Tile.East.ordinal && groupToTile[deck[1]].first>=Tile.East.ordinal &&
                        groupToTile[deck[2]].first>=Tile.East.ordinal && groupToTile[deck[3]].first>=Tile.East.ordinal &&
                        deck[4]>=Tile.East.ordinal) &&
                //不能全部都是数牌
                !(groupToTile[deck[0]].first<Tile.East.ordinal && groupToTile[deck[1]].first<Tile.East.ordinal &&
                        groupToTile[deck[2]].first<Tile.East.ordinal && groupToTile[deck[3]].first<Tile.East.ordinal &&
                        deck[4]<Tile.East.ordinal) &&
                //不能是对对和
                !AllSet.check(deck)
    }
}