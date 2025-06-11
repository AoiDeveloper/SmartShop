package com.github.aoideveloper.smartShop.gui

import com.github.aoideveloper.smartShop.shop.ShopImpl
import mc.obliviate.inventory.Gui
import mc.obliviate.inventory.Icon
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryOpenEvent

class ConfirmBuyGUI(player: Player, val itemId : Long): Gui(player, "confirm-gui", "購入確認", 3) {
    override fun onOpen(event: InventoryOpenEvent) {
        addItem(Icon(Material.GREEN_WOOL).setName("${ChatColor.GREEN}購入する").onClick {
            ShopImpl.buy(itemId, player.uniqueId)
            player.closeInventory()
        }, 16)
        addItem(Icon(Material.RED_WOOL).setName("${ChatColor.RED}キャンセル").onClick {
            BuyGUI(player).open()
        }, 10)
    }
}
