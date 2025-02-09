package net.serlith.bedwarstnt.util

import net.serlith.bedwarstnt.exceptions.ReloadException

abstract class Reloadable (
    private val priority: Int
) {

    init {
        reloadables.add(this)
    }

    companion object {
        private val reloadables = mutableListOf<Reloadable>()
        fun reload() {
            this.reloadables.sortedBy(Reloadable::priority).forEach(Reloadable::reload)
        }
    }

    @Throws(ReloadException::class)
    abstract fun reload()

}