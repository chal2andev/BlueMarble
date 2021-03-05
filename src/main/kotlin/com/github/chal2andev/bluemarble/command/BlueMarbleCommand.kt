package com.github.chal2andev.bluemarble.command

import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import com.github.chal2andev.bluemarble.system.GameMain
import com.github.monun.kommand.KommandDispatcherBuilder
import org.bukkit.ChatColor
import org.bukkit.entity.Player

internal object BlueMarbleCommand {

    private lateinit var plugin: BlueMarblePlugin
    private lateinit var gamemain: GameMain

    internal fun initModule(plugin: BlueMarblePlugin, gamemain: GameMain){
        this.plugin = plugin
        this.gamemain = gamemain
    }

    internal fun register(builder: KommandDispatcherBuilder){
        builder.register("bluemarble"){
            require { this is Player }
            executes {
                context ->
                bluemarble(context.sender as Player)
            }
            then("start"){
                executes {
                        context ->
                    start(context.sender as Player)
                }
            }
            then("stop"){
                executes{
                    context ->
                    stop(context.sender as Player)
                }
                then("confirm"){
                        executes {
                            context ->
                            stopConfirm(context.sender as Player)
                        }
                }
            }
        }
    }

    private val prefix: String = "${ChatColor.GOLD}[BlueMarble] ${ChatColor.YELLOW}"


    private fun bluemarble(sender: Player){
        if(sender.isOp){
            sender.sendMessage("${prefix}사용법 - /bluemarble [start | stop]")
        }else{
            sender.sendMessage("${prefix}권한이 없습니다.")
        }

    }
    private fun start(sender: Player){
        if(sender.isOp){

            gamemain.onStart(sender)
        }else {
            sender.sendMessage("${ChatColor.RED}알 수 없는 명령입니다.")
        }
    }
    private fun stop(sender: Player){
        sender.sendMessage("${prefix}종료하시려면 /bluemarble stop confirm 명령을 입력해주세요.")
    }
    private fun stopConfirm(sender:Player){
        gamemain.onStop(sender)
    }
}