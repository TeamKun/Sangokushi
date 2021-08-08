package net.kunmc.lab.sangokushi.util

class XYList<T> {
    private val list: MutableMap<Int, MutableMap<Int, T>> = mutableMapOf()

    operator fun get(x: Int, y: Int): T? {
        if (list[x] == null) {
            return null
        }

        return list[x]!![y]
    }

    operator fun set(x: Int, y: Int, t: T) {
        if (list[x] == null) {
            list[x] = mutableMapOf()
        }

        list[x]!![y] = t
    }

    fun forEach(f: (T) -> Unit) {
        forEachIndexed { _, t -> f(t) }
    }

    fun forEachIndexed(f: (Pair<Int, Int>, T) -> Unit) {
        list.forEach { (x, u) ->
            u.forEach { (y, t) ->
                f(Pair(x,y),t)
            }
        }
    }
}