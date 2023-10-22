package com.redfootdev.example

import org.bukkit.plugin.java.JavaPlugin


class ExamplePlugin() : JavaPlugin() {

    override fun onEnable() {
        logger.info { "ExamplePlugin Initializing " }
    }

    override fun onDisable() {
        logger.info { "ExamplePlugin Shutting Down" }
    }

}