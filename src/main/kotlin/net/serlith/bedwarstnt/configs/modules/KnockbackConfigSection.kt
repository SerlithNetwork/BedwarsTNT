package net.serlith.bedwarstnt.configs.modules

import org.bukkit.configuration.ConfigurationSection

class KnockbackConfigSection (
    val horizontalExtra: Double,
    val verticalExtra: Double,
    val multiplier: Double,
    val speedLimit: Double,
) {
    companion object {
        @JvmStatic
        fun deserialize(section: ConfigurationSection) = KnockbackConfigSection(
            section.getDouble("horizontal-extra"),
            section.getDouble("vertical-extra"),
            section.getDouble("multiplier"),
            section.getDouble("speed-limit")
        )
    }
}