package com.playtheatria.jliii.generalutils.config;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class ConfigManager<T extends JavaPlugin> {
    private final T plugin;
    private boolean debug;

    public ConfigManager(T plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    /**
     * Use this for reload Commands.
     */
    public void reloadConfig(){
        plugin.reloadConfig();
        loadConfig();
    };

    /**
     * Load any additional variables you are storing in ConfigManager.
     */
    public abstract void loadConfig();

    public boolean isDebug() {
        return debug;
    }

    public String getPluginName() {
        return plugin.getName();
    }
}
