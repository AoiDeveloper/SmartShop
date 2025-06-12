package com.github.aoideveloper.smartShop.gui

import com.github.aoideveloper.simpleEconomy.SimpleEconomy
import mc.obliviate.inventory.Gui
import mc.obliviate.inventory.Icon
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class ShopGUI(player: Player): Gui(player, "shop-gui", "ショップ", 3) {
    override fun onOpen(event: InventoryOpenEvent?) {
        val sellItem = Icon(Material.CHEST).setName("${ChatColor.GOLD}アイテムを売る").onClick {
            SellGUI(player).open()
        }
        val buyItem = Icon(Material.EMERALD).setName("${ChatColor.GREEN}アイテムを買う").onClick {
            BuyGUI(player).open()
        }
        val sellerItem = Icon(Material.ENDER_CHEST).setName("${ChatColor.LIGHT_PURPLE}出品者メニュー").onClick {
            SellerGUI(player).open()
        }
        val playerHead = ItemStack(Material.PLAYER_HEAD)
        val meta = playerHead.itemMeta as SkullMeta
        meta.owningPlayer = player
        playerHead.itemMeta = meta
        val profileIcon = Icon(playerHead).setName("現在の所持金:${SimpleEconomy.service.getBalance(player.uniqueId)}")
        addItem(10, sellItem)
        addItem(16, buyItem)
        addItem(13, sellerItem)
        addItem(26, profileIcon)
    }
}