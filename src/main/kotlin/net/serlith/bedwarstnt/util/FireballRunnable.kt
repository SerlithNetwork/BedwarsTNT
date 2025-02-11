package net.serlith.bedwarstnt.util

import org.bukkit.Location
import org.bukkit.entity.Fireball
import org.bukkit.scheduler.BukkitRunnable

class FireballRunnable (
    private val fireball: Fireball,
    private val origin: Location,
    private val speed: Double,
    private val despawnDistance: Long,
) : BukkitRunnable() {

    override fun run() {
        if (origin.distance(fireball.location) > despawnDistance) {
            if (this.fireball.isValid) this.fireball.remove()
            this.cancel()
        }

        val direction = fireball.direction.normalize().multiply(speed)
        this.fireball.velocity = direction
    }

}