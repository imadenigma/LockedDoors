package me.imadenigma.lockeddoors.listeners

import me.imadenigma.lockeddoors.toUser
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        e.player.toUser()
    }

}