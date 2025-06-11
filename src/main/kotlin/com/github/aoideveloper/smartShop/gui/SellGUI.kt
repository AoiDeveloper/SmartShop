package com.github.aoideveloper.smartShop.gui

import com.github.aoideveloper.smartShop.shop.ShopImpl
import mc.obliviate.inventory.Gui
import mc.obliviate.inventory.Icon
import mc.obliviate.inventory.advancedslot.AdvancedSlotManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.max

class SellGUI(player: Player): Gui(player, "sell-gui", "売却する", 3) {
    var advancedSlotManager: AdvancedSlotManager = AdvancedSlotManager(this)
    var sellPrice: Double = 100.0
        set(price) {
            field = max(0.0, price)
            val priceStack = ItemStack(Material.EMERALD)
            priceStack.itemMeta = priceStack.itemMeta?.apply {
                setDisplayName("売値：$field")
            }
            val priceIcon = Icon(priceStack)
            addItem(15, priceIcon)
        }


    override fun onOpen(event: InventoryOpenEvent) {
        fillGui(Icon(Material.GRAY_STAINED_GLASS_PANE))
        fillColumn(Icon(Material.BLACK_STAINED_GLASS_PANE), 0)
        fillColumn(Icon(Material.BLACK_STAINED_GLASS_PANE), 1)
        fillColumn(Icon(Material.BLACK_STAINED_GLASS_PANE), 2)

        val addPriceItem = Icon(Material.GREEN_DYE).setName("売値を1.0増やす").onClick {
            sellPrice += 1.0
        }
        val addPrice10Item = Icon(Material.GREEN_DYE).setName("売値を10.0増やす").onClick {
            sellPrice += 10.0
        }
        val subPriceItem = Icon(Material.GREEN_DYE).setName("売値を1.0減らす").onClick {
            sellPrice -= 1.0
        }
        val subPrice10Item = Icon(Material.GREEN_DYE).setName("売値を10.0減らす").onClick {
            sellPrice -= 10.0
        }
        val itemSlot = advancedSlotManager.addAdvancedIcon(10, Icon(Material.AIR))
        val sellConfirmItem = Icon(Material.SPECTRAL_ARROW).setName("売却する").onClick {
            if(itemSlot.itemStack != null) {
                ShopImpl.sell(event.player.uniqueId, sellPrice, itemSlot.itemStack)
                itemSlot.reset()
            }
       }
        addItem(5, addPriceItem)
        addItem(4, addPrice10Item)
        addItem(16, sellConfirmItem)
        addItem(23, subPriceItem)
        addItem(22, subPrice10Item)
        sellPrice = 100.0
    }
}