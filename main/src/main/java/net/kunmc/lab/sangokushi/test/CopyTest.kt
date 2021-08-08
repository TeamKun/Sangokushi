package net.kunmc.lab.sangokushi.test

import net.kunmc.lab.sangokushi.Sangokushi
import net.kunmc.lab.sangokushi.util.copy
import net.kunmc.lab.sangokushi.world.BlockSet
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class CopyTest(p: Sangokushi) : Listener {
    companion object {
        var instance: CopyTest? = null
        val clipBoard: MutableMap<Player, BlockSet> = mutableMapOf()
        val isCopyMode: MutableMap<Player, Boolean> = mutableMapOf()
        val isCopyState: MutableMap<Player, Pair<Location?, Location?>> = mutableMapOf()

        val isPasteMode: MutableMap<Player, Boolean> = mutableMapOf()
    }

    init {
        instance = this
        p.server.pluginManager.registerEvents(this, p)
    }

    @EventHandler
    fun onBreakBlock(e: BlockBreakEvent) {
        if (isCopyMode.getOrDefault(e.player, false)) {
            if (!isCopyState.containsKey(e.player)) {
                isCopyState[e.player] = Pair(null, null)
            }

            if (isCopyState[e.player]!!.first == null) {
                isCopyState[e.player] = Pair(e.block.location.copy(), null)
            } else {
                isCopyState[e.player] = Pair(isCopyState[e.player]!!.first, e.block.location.copy())
                clipBoard[e.player] = BlockSet.copy(isCopyState[e.player]!!.first!!, isCopyState[e.player]!!.second!!)
                isCopyState[e.player] = Pair(null,null)
                isCopyMode[e.player] = false
            }

            e.isCancelled = true
        }

        if (isPasteMode.getOrDefault(e.player, false)) {
            if (isCopyMode.getOrDefault(e.player, true)) {
                // Copy Mode is On
                e.player.sendMessage("You are copying")
            } else {
                val clipBoard = clipBoard[e.player]
                if (clipBoard == null) {
                    e.player.sendMessage("Your clipBoard is empty")
                } else {
                    clipBoard.copyTo(e.block.location)
                    e.player.sendMessage("Copied!")
                    isPasteMode[e.player] = false
                    e.isCancelled = true
                }
            }
        }
    }
}