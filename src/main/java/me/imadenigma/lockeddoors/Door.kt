package me.imadenigma.lockeddoors

import org.bukkit.block.Block


class Door(val door: Array<Block>, var password: String = "", var owner: User? = null) {
    init {
        allDoors.add(this)
    }
    companion object {
        val allDoors = mutableListOf<Door>()
    }
}