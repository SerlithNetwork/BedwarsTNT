package net.serlith.bedwarstnt.configs

import net.serlith.bedwarstnt.BedwarsTNT
import net.serlith.bedwarstnt.exceptions.ReloadException
import net.serlith.bedwarstnt.util.Reloadable
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class MessagesConfig (
    private val plugin: BedwarsTNT
) : Reloadable(0) {

    private val fileName = "messages.yml"
    private val configPath = File(this.plugin.dataFolder, fileName)
    private var config = YamlConfiguration.loadConfiguration(configPath)

    init {
        this.reload()
    }

    lateinit var messagesSection: MessagesConfigSection
        private set
    class MessagesConfigSection (
        val reloadFailed: String,
        val reloadSuccessful: String,
        val noPermission: String,
        val invalidCommand: String,
        val tntCooldown: String,
        val fireballCooldown: String,
        val knockbackDetails: List<String>,
        val otherDetails: List<String>,
    ) {
        companion object {
            fun deserialize(section: ConfigurationSection) = MessagesConfigSection(
                section.getString("reload-failed").replace("&", "§"),
                section.getString("reload-successful").replace("&", "§"),
                section.getString("no-permission").replace("&", "§"),
                section.getString("invalid-command").replace("&", "§"),
                section.getString("tnt-cooldown").replace("&", "§"),
                section.getString("fireball-cooldown").replace("&", "§"),
                section.getStringList("knockback-details").map { it.replace("&", "§") },
                section.getStringList("other-details").map { it.replace("&", "§") },
            )
        }
    }

    override fun reload() {
        if (!this.configPath.exists()) {
            this.plugin.saveResource(this.fileName, false)
        }

        try {
            this.config = YamlConfiguration.loadConfiguration(this.configPath)
            this.messagesSection = MessagesConfigSection.deserialize(this.config.getConfigurationSection("messages"))
        } catch (_: Exception) {
            throw ReloadException(this.fileName)
        }
    }

}