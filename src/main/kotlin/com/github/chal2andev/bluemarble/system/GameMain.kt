package com.github.chal2andev.bluemarble.system

import com.github.chal2andev.bluemarble.plugin.BlueMarblePlugin
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.scoreboard.*
import java.util.*

object GameMain {

    private lateinit var plugin: BlueMarblePlugin
    internal fun initModule(plugin: BlueMarblePlugin){
        this.plugin = plugin
    }

    private val prefix: String = "${ChatColor.GOLD}[BlueMarble] ${ChatColor.YELLOW}"

    var rungame: Boolean = false
    fun onStart(sender: Player) {

        val scheduler = Bukkit.getScheduler()

        if (rungame == true) {
            sender.sendMessage("${prefix}게임이 시작되어 있습니다.")
        } else {
            sender.sendMessage("${prefix}부루마불 게임이 시작됩니다.")
            rungame = true
            for (players: Player in Bukkit.getServer().onlinePlayers) {
                players.sendTitle("${ChatColor.AQUA}BLUE MARBLE", "", 40, 60, 40)
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, Runnable(){
                setMoney()
                for (players: Player in Bukkit.getServer().onlinePlayers) {
                    players.sendTitle("", "${ChatColor.YELLOW}기본 게임머니가 주어졌습니다.", 10, 40, 10)
                    playTo(sender, players.location)
                }
            }, 140)
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, Runnable(){
                for (players: Player in Bukkit.getServer().onlinePlayers) {
                    players.sendTitle("", "${ChatColor.YELLOW}남들보다 더 많은 땅을 구매하여 승리하세요!", 10, 40, 10)
                    playTo(sender, players.location)
                }
            }, 200)
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, Runnable(){
                for (players: Player in Bukkit.getServer().onlinePlayers) {
                    players.sendTitle("", "${ChatColor.YELLOW}순서를 정하기 위해 주사위를 굴립니다.", 10, 40, 10)
                    playTo(sender, players.location)
                }
            }, 260)
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, Runnable(){
                for (players: Player in Bukkit.getServer().onlinePlayers) {
                    onDiceGUI(players)
                }

            }, 300)
        }
    }
    fun onStop(sender: Player){
        if (rungame == true){
            rungame = false
            sender.sendMessage("${prefix}게임을 강제종료 하였습니다.")
        }else{
            sender.sendMessage("${prefix}게임이 시작되어 있지 않습니다.")
        }

    }
    private fun setMoney(){
        val score: ScoreboardManager = Bukkit.getScoreboardManager()
        val board: Scoreboard = score.newScoreboard
        val obj: Objective = board.registerNewObjective("money", "dummy")
        obj.displaySlot = DisplaySlot.SIDEBAR
        obj.displayName = "${ChatColor.AQUA}${ChatColor.BOLD}MONEY"
        for (online: Player in Bukkit.getServer().onlinePlayers){
            val money: Score = obj.getScore(online)
            money.score = 2830000
            online.scoreboard = board
        }

    }
    private fun playTo(player: Player, location: Location) {
        player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f)
    }
    private fun onDiceGUI(player: Player){

        val dice: ItemStack = ItemStack(Material.RED_MUSHROOM_BLOCK)
        val meta: ItemMeta = dice.itemMeta
        meta.setDisplayName("${ChatColor.GREEN}주사위 던지기")
        meta.lore = Arrays.asList("${ChatColor.YELLOW}주사위를 던집니다.")
        dice.setItemMeta(meta)

        val inv: Inventory = Bukkit.createInventory(null,9,"${ChatColor.GREEN}DICE")
        inv.setItem(4, dice)
        player.openInventory(inv)

    }
}