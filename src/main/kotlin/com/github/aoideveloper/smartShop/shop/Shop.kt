package com.github.aoideveloper.smartShop.shop

import org.bukkit.inventory.ItemStack
import java.util.UUID

data class SellingItem(val seller: UUID, val price: Double, val item: ItemStack, val id : Long)

interface Shop {
    fun sell(seller: UUID, price: Double, item: ItemStack) : Long
    fun buy(itemId: Long, buyer: UUID) : Boolean
    fun items() : List<SellingItem>
}