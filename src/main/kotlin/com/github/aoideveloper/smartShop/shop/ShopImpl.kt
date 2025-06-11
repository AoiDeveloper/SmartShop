package com.github.aoideveloper.smartShop.shop

import com.github.aoideveloper.simpleEconomy.SimpleEconomy
import com.github.aoideveloper.smartShop.SmartShop
import com.github.aoideveloper.smartShop.database.ShopItems
import com.github.aoideveloper.smartShop.uitl.itemStackFromBase64Record
import com.github.aoideveloper.smartShop.uitl.toBase64Record
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.update
import java.util.UUID


object ShopImpl: Shop {
    override fun sell(seller: UUID, price: Double, item: ItemStack): Long {
        return transaction {
            val insertedRow = ShopItems.insert {
                it[ShopItems.seller] = seller
                it[ShopItems.item] = item.toBase64Record()
                it[ShopItems.price] = price
                it[ShopItems.sold] = false
                it[ShopItems.removed] = false
            }
            insertedRow[ShopItems.id]
        }
    }

    override fun buy(itemId: Long, buyer: UUID): Boolean {
        return transaction {
            val buyItem = ShopItems.selectAll().where{ (ShopItems.id eq itemId) and (ShopItems.sold eq false) }.singleOrNull() ?: return@transaction false
            val seller = buyItem[ShopItems.seller]

            val player = SmartShop.plugin.server.getPlayer(buyer)

            if(seller == buyer) {
                player?.sendMessage("${ChatColor.GOLD}自分が出品したアイテムは買えません。もし出品を取り下げたい場合にはショップメニューからどうぞ")
                return@transaction false
            }

            val transferResult = SimpleEconomy.service.transfer(buyer, seller, buyItem[ShopItems.price])
            if(transferResult) {
                ShopItems.update({(ShopItems.id eq itemId) and (ShopItems.sold eq false)}) {
                    it[ShopItems.sold] = true
                }
                val stack = itemStackFromBase64Record(buyItem[ShopItems.item])
                player?.inventory?.addItem(stack)
                player?.sendMessage("${ChatColor.GOLD}アイテム「${stack.type.name}」を${buyItem[ShopItems.price]}で購入しました！現在のあなたの所持金は${ChatColor.AQUA}${SimpleEconomy.service.getBalance(player.uniqueId)}${ChatColor.GOLD}です！")
            }
            else {
                player?.sendMessage("${ChatColor.GOLD}あらら！アイテムを購入できませんでした。お金が足りなかったのかな？")
            }
            return@transaction transferResult
        }
    }

    override fun items(): List<SellingItem> {
        return transaction {
            ShopItems.selectAll().where{ ShopItems.sold eq false }.map {
                SellingItem(it[ShopItems.seller], it[ShopItems.price], itemStackFromBase64Record(it[ShopItems.item]), it[ShopItems.id])
            }
        }
    }
}