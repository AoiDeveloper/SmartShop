package com.github.aoideveloper.smartShop.database

import org.jetbrains.exposed.v1.core.Table

object ShopItems : Table("shop_items") {
    val id = long("id").autoIncrement()

    val seller = uuid("seller")
    val price = double("value")
    val item = text("item")
    val sold = bool("sold")
    val removed = bool("removed")

    override val primaryKey = PrimaryKey(id)
}