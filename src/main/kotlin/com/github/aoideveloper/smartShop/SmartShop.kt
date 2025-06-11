package com.github.aoideveloper.smartShop

import com.github.aoideveloper.simpleEconomy.SimpleEconomy
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class SmartShop : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        val currentB = SimpleEconomy.service.getBalance(UUID.randomUUID())
        logger.info(currentB.toString())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
