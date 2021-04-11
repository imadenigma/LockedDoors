package me.imadenigma.lockeddoors.listeners

import me.imadenigma.lockeddoors.Door
import me.imadenigma.lockeddoors.guis.CreateGui
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class DoorListener : Listener {

    @EventHandler
    fun onDoorClick(e: PlayerInteractEvent) {
        if (!e.hasBlock()) return
        if (!isDoor(e.clickedBlock!!)) return
        var door: Door? = null
        Door.allDoors.stream().filter { it.door.contains(e.clickedBlock) }.findAny().ifPresent { door = it }
        if (door == null) {
            val blocks = mutableListOf(e.clickedBlock!!)
            if (e.clickedBlock!!.getRelative(BlockFace.DOWN).type == e.clickedBlock!!.type) blocks.add(
                e.clickedBlock!!.getRelative(
                    BlockFace.DOWN
                )
            )
            else if (e.clickedBlock!!.getRelative(BlockFace.UP).type == e.clickedBlock!!.type) blocks.add(
                e.clickedBlock!!.getRelative(
                    BlockFace.UP
                )
            )
            door = Door(blocks.toTypedArray())
        }
        if (door?.password.equals("")) {
            CreateGui(e.player)
        }
    }

    private fun isDoor(block: Block): Boolean {
        return block.type.name.contains("door", true) && !block.type.name.contains("trap", true)
    }
}