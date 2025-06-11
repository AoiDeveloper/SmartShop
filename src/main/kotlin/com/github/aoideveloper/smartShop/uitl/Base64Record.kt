package com.github.aoideveloper.smartShop.uitl

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


fun ItemStack.toBase64Record(): String {
    val outputStream: ByteArrayOutputStream = ByteArrayOutputStream()
    val dataOutput: BukkitObjectOutputStream = BukkitObjectOutputStream(outputStream)

    dataOutput.writeObject(this)
    dataOutput.close()
    return Base64Coder.encodeLines(outputStream.toByteArray())
}

fun itemStackFromBase64Record(data: String): ItemStack {
    val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
    val dataInput = BukkitObjectInputStream(inputStream)

    val item = dataInput.readObject() as ItemStack
    dataInput.close()
    return item
}