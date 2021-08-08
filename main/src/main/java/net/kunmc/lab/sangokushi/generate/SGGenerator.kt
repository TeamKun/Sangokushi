package net.kunmc.lab.sangokushi.generate

import net.kunmc.lab.sangokushi.Sangokushi
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.WorldInitEvent

class SGGenerator(p:Sangokushi):Listener{
    init {
        p.server.pluginManager.registerEvents(this,p)
    }

    @EventHandler
    fun onGenerate(e:WorldInitEvent){

    }
}