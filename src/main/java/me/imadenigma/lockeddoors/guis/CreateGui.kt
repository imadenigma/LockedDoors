package me.imadenigma.lockeddoors.guis

import me.imadenigma.lockeddoors.LockedDoors
import me.imadenigma.lockeddoors.toUser
import me.mattstudios.mfgui.gui.components.ItemBuilder
import me.mattstudios.mfgui.gui.guis.Gui
import me.mattstudios.mfgui.gui.guis.GuiItem
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CreateGui(private val player: Player) {
    private val gui = Gui(3, "§4Create a password")

    init {
        val fillItem = GuiItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE) { e -> e.isCancelled = true }
        gui.filler.fill(fillItem)
        addItems()
        this.gui.open(player)
    }

    private fun addItems() {
        val colorCode = LockedDoors.configuration.config.node("NumbersButtonColor").getString("0").colorize()
        for (i in 10..16) {
            val button =
                ItemBuilder.from(Material.CONDUIT).setName(colorCode + 0)
                    .asGuiItem {
                        it.isCancelled = true
                        var number = it.currentItem?.itemMeta?.displayName?.removePrefix(colorCode)?.toInt() ?: 0
                        if (it.isLeftClick) {
                            number++
                        }else number--
                        it.currentItem?.updateName(colorCode + number)
                    }
            gui.setItem(i, button)
        }
        val exitColor = LockedDoors.configuration.config.node("ExitButtonColor").getString("0").colorize()
        val enterColor = LockedDoors.configuration.config.node("EnterButtonColor").getString("0").colorize()
        val cancelButton = ItemBuilder.from(Material.BARRIER).setName(exitColor + "Close").asGuiItem {
            it.isCancelled = true
            it.whoClicked.closeInventory()
        }
        val enterButton = ItemBuilder.from(Material.ARROW).setName(enterColor + "Enter").asGuiItem {
            it.isCancelled = true
            val wordBuilder = StringBuilder()
            for (i in 10..16) {
                val item = this.gui.getGuiItem(i) ?: return@asGuiItem
                val num = item.itemStack?.itemMeta?.displayName?.removePrefix(colorCode)?.toInt()
                wordBuilder.append(num)
            }
            val door = player.toUser().lastClickedDoor!!
            door.password = wordBuilder.toString()
            door.owner = player.toUser()
        }
        this.gui.setItem(22,cancelButton)
        this.gui.setItem(26,enterButton)
    }
    @Suppress("MemberVisibilityCanBePrivate")
    fun String.colorize(): String {
        return ChatColor.translateAlternateColorCodes('&', this)
    }
    private fun ItemStack.updateName(name: String) {
        val meta = this.itemMeta ?: return
        meta.setDisplayName(name)
        this.itemMeta = meta
    }


}