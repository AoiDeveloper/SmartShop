package com.github.aoideveloper.smartShop.command

import com.github.aoideveloper.smartShop.gui.ShopGUI
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ShopCommand: CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        name: String,
        args: Array<out String?>
    ): Boolean {
        if(sender !is Player) return false
        ShopGUI(sender).open()
        return true
    }
}