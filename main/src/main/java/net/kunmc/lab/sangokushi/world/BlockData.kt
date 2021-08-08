package net.kunmc.lab.sangokushi.world

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData

class BlockData(
    val data: BlockData,
    val material:Material
) {
    constructor(loc: Location) : this(loc.block)
    constructor(block: Block) : this(block.blockData.clone(),block.type)


    fun copyTo(loc: Location): Block {
        // さてどうなるか
        loc.block.type = material
        loc.block.blockData = data
        return loc.block
    }
}