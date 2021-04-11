package me.imadenigma.lockeddoors

import java.util.*

class User(val uuid: UUID) {

    private val doors = mutableListOf<Door>()

    var lastClickedDoor: Door? = null

    fun addDoor(door: Door) {
        this.doors.add(door)
    }

    fun removeDoor(door: Door) {
        doors.remove(door)
    }


    init {
        allUsers.add(this)
    }

    companion object {
        val allUsers = mutableListOf<User>()
    }
}