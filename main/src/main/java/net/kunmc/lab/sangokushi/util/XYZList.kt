package net.kunmc.lab.sangokushi.util

class XYZList<T> {
    private val list: MutableMap<Int, MutableMap<Int, MutableMap<Int, T>>> = mutableMapOf()

    operator fun get(x: Int, y: Int, z: Int): T? {
        if (list[x] == null) {
            return null
        }

        if (list[x]!![y] == null) {
            return null
        }

        return list[x]!![y]!![z]
    }

    operator fun set(x: Int, y: Int, z: Int, t: T) {
        if (list[x] == null) {
            list[x] = mutableMapOf()
        }

        if (list[x]!![y] == null) {
            list[x]!![y] = mutableMapOf()
        }

        list[x]!![y]!![z] = t
    }

    fun forEach(f: (T) -> Unit) {
        forEachIndexed { tuple, t -> f(t) }
    }

    fun forEachIndexed(f: (Tuple<Int, Int, Int>, T) -> Unit) {
        list.forEach { (x, u) ->
            u.forEach { (y, i) ->
                i.forEach { (z, t) ->
                    f(Tuple(x, y, z), t)
                }
            }
        }
    }

    fun size(): Int {
        var count = 0
        forEach { count++ }
        return count
    }
}