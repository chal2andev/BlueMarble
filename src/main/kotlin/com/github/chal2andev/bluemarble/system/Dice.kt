package com.github.chal2andev.bluemarble.system

object Dice{
    fun roll(): Int {
        val dice: Int = ((Math.random()*6) + 1).toInt()
        return dice
    }
}