package com.github.aoideveloper.smartShop.gui

import com.github.aoideveloper.smartShop.shop.ShopImpl
import mc.obliviate.inventory.Gui
import mc.obliviate.inventory.Icon
import mc.obliviate.inventory.pagination.PaginationManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryOpenEvent

class BuyGUI(player: Player): Gui(player, "buy-gui", "購入可能アイテム", 4) {
    val pagination = PaginationManager(this)
    override fun onOpen(event: InventoryOpenEvent?) {
        pagination.registerPageSlotsBetween(0, 26)
        println(ShopImpl.items())
        ShopImpl.items().forEach {
            val item = Icon(it.item)
            item.setLore("${ChatColor.GRAY}Sold by: ${ChatColor.YELLOW}${Bukkit.getOfflinePlayer(it.seller).name}",
                "${ChatColor.GRAY}Price: ${ChatColor.GREEN}${it.price}",
                "",
                "${ChatColor.DARK_GRAY}ID: ${it.id}")

            val id = it.id
            item.onClick {
                ConfirmBuyGUI(player, id).open()
            }
            pagination.addItem(item)
        }
        val nextPageItem = Icon(Material.ARROW).setName("${ChatColor.AQUA}次ページへ進む").onClick {
            if(pagination.isLastPage) pagination.goFirstPage()
            else pagination.goNextPage()
            pagination.update()
        }
        addItem(nextPageItem, 35)
        pagination.update()
    }
}