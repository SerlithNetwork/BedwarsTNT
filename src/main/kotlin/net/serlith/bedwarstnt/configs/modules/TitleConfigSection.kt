package net.serlith.bedwarstnt.configs.modules

import org.bukkit.configuration.ConfigurationSection

class TitleConfigSection (
    val fadeIn: Int,
    val stay: Int,
    val fadeOut: Int,
) {
    companion object {
        @JvmStatic
        fun deserialize(section: ConfigurationSection) = TitleConfigSection(
            section.getInt("fade-in"),
            section.getInt("stay"),
            section.getInt("fade-out"),
        )
    }
}