package com.github.chal2andev.bluemarble.plugin

import com.github.chal2andev.bluemarble.command.BlueMarbleCommand
import com.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class BlueMarblePlugin : JavaPlugin(){
    override fun onEnable() {
        setupCommands()
    }

    override fun onDisable() {

    }
    private fun setupCommands(){
        kommand {
            BlueMarbleCommand.register(this)
        }
    }
}