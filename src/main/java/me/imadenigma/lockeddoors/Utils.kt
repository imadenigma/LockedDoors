package me.imadenigma.lockeddoors

import com.google.common.base.Suppliers
import org.bukkit.entity.Player

fun Player.toUser(): User {
    return User.allUsers.stream().filter { it.uuid == this.uniqueId }.findAny()
        .orElseGet(Suppliers.ofInstance(User(this.uniqueId)))
}