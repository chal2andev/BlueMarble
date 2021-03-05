package com.github.chal2andev.bluemarble.plugin

import com.github.chal2andev.bluemarble.command.BlueMarbleCommand
import com.github.chal2andev.bluemarble.event.BlueMarbleEvent
import com.github.chal2andev.bluemarble.system.GameMain
import com.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class BlueMarblePlugin : JavaPlugin(){
    override fun onEnable() {
        setupGameMain()
        setupCommands()
        registerEvent()
    }

    override fun onDisable() {

    }
    private fun setupCommands(){
        BlueMarbleCommand.initModule(this, GameMain)
        kommand {
            BlueMarbleCommand.register(this)
        }
    }
    private fun setupGameMain(){
        GameMain.initModule(this)
    }
    private fun registerEvent(){
        server.pluginManager.registerEvents(BlueMarbleEvent(),this)
    }
}