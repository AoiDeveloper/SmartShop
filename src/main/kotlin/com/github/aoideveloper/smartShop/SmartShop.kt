package com.github.aoideveloper.smartShop

import com.github.aoideveloper.smartShop.database.DatabaseManager
import com.github.aoideveloper.smartShop.command.ShopCommand
import mc.obliviate.inventory.InventoryAPI
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class SmartShop : JavaPlugin() {
    private lateinit var databaseManager: DatabaseManager

    companion object {
        lateinit var plugin: Plugin
    }
    override fun onEnable() {
        // Plugin startup logic
        plugin = this

        InventoryAPI(this).init()

        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }

        databaseManager = DatabaseManager(dataFolder)
        databaseManager.connect()

        getCommand("shop")?.setExecutor(ShopCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
