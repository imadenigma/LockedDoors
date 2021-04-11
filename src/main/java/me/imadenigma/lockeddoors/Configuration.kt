package me.imadenigma.lockeddoors

import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File

class Configuration {
    lateinit var config: ConfigurationNode
    init {
        val file = File(LockedDoors.singleton.dataFolder, "config.yml")
        if (!file.exists()) {
            file.parentFile.mkdir()
            file.createNewFile()
        }

        val loader = YamlConfigurationLoader.builder()
            .file(file)
            .build()

        if (loader.canLoad())
            config = loader.load()
    }
}