package net.kunmc.lab.sangokushi.world

import net.kunmc.lab.sangokushi.util.XYZList
import net.kunmc.lab.sangokushi.util.add
import net.kunmc.lab.sangokushi.util.copy
import org.bukkit.Location
import org.bukkit.Material
import java.lang.Integer.max
import java.lang.Integer.min

class BlockSet(val block: XYZList<BlockData>) {
    companion object {
        fun copy(from: Location, to: Location): BlockSet {
            val list = XYZList<BlockData>()


            val minX = min(from.blockX, to.blockX)
            val minY = min(from.blockY, to.blockY)
            val minZ = min(from.blockZ, to.blockZ)

            val maxX = max(from.blockX, to.blockX)
            val maxY = max(from.blockY, to.blockY)
            val maxZ = max(from.blockZ, to.blockZ)


            for (x in minX..maxX) {
                for (y in minY..maxY) {
                    for (z in minZ..maxZ) {
                        list[x - minX, y - minY, z - minZ] = BlockData(
                            from.world.getBlockAt(x, y, z)
                        )
                    }
                }
            }

            return BlockSet(list)
        }
    }


    fun copyTo(loc: Location) {
        block.forEachIndexed { tuple, blockData ->
            val l = loc.copy().add(tuple)
            blockData.copyTo(l)
        }
    }
}