package com.github.chal2andev.bluemarble.command

import com.github.monun.kommand.KommandDispatcherBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object BlueMarbleCommand {
    internal fun register(builder: KommandDispatcherBuilder){
        builder.register("bluemarble"){
            require { this is Player }
            executes {
                context ->
                bluemarble(context.sender as Player)
            }
        }
    }
    private fun bluemarble(sender: Player){
        sender.sendMessage("${ChatColor.GOLD}BlueMarble Test")
    }
}