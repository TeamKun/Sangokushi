package net.kunmc.lab.sangokushi.util

import org.bukkit.Location
import org.jetbrains.annotations.NotNull

fun Location.copy(): Location {
    return Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch)
}

@JvmName("addi")
fun Location.add(tuple: Tuple<Int, Int, Int>): Location {
    return this.add(tuple.a.toDouble(), tuple.b.toDouble(), tuple.c.toDouble())
}

@JvmName("addd")
fun Location.add(tuple: Tuple<Double, Double, Double>): Location {
    return this.add(tuple.a, tuple.b, tuple.c)
}

