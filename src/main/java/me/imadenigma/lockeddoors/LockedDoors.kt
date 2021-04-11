package me.imadenigma.lockeddoors

import me.imadenigma.lockeddoors.listeners.DoorListener
import me.imadenigma.lockeddoors.listeners.PlayerListeners
import org.bukkit.plugin.java.JavaPlugin

class LockedDoors : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        singleton = this
        configuration = Configuration()
        server.pluginManager.registerEvents(DoorListener(),this)
        server.pluginManager.registerEvents(PlayerListeners(),this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }






    companion object {
        lateinit var singleton: LockedDoors
        lateinit var configuration: Configuration
    }
}