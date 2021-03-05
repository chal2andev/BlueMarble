package com.github.chal2andev.bluemarble.event

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

class BlueMarbleEvent: Listener {
    @EventHandler
    fun inventoryClick(e: InventoryClickEvent){
        val p: Player = e.whoClicked as Player
        val inv: Inventory = e.clickedInventory as Inventory

        if (e.currentItem?.type == Material.RED_MUSHROOM_BLOCK){
            e.isCancelled = true
        }
    }
    /*fun inventoryClose(e: InventoryCloseEvent){
        val inv: Inventory = e.inventory.
    }*/
}