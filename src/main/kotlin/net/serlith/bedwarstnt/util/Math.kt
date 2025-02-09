package net.serlith.bedwarstnt.util

import org.bukkit.entity.Entity
import org.bukkit.util.Vector

fun extraMomentum(
    target: Entity,
    source: Entity,
    horizontal: Double,
    vertical: Double,
    multiplier: Double
): Vector {
    var vector = target.location.toVector().subtract(source.location.toVector())
    val extra = Vector(
        horizontal * (vector.x / (vector.x + vector.z)),
        vertical,
        horizontal * (vector.z / (vector.x + vector.z)),
    )

    vector = vector.add(extra)
    val length = vector.length()
    vector = vector.normalize()

    return vector.multiply(multiplier / length)
}